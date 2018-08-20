package com.m5173.allureLove.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.m5173.allureLove.common.base.BaseService;
import com.m5173.allureLove.common.constants.CommonConstants;
import com.m5173.allureLove.common.enums.ResponseCodes;
import com.m5173.allureLove.common.exception.BusinessException;
import com.m5173.allureLove.common.util.BeanMapper;
import com.m5173.allureLove.common.util.CookieUtils;
import com.m5173.allureLove.common.util.Digests;
import com.m5173.allureLove.common.util.UrlBase64;
import com.m5173.allureLove.dao.UserEOMapper;
import com.m5173.allureLove.model.eo.UserEO;
import com.m5173.allureLove.model.request.UserRequest;
import com.m5173.allureLove.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * 后台用户业务层
 * Created by 340067 on 2018/4/24.
 */
@Component
public class UserServiceImpl extends BaseService<UserEO> implements IUserService {

    @Autowired
    private UserEOMapper userEOMapper;

    @Value("${user.login.expire.time}")
    private long loginExpireTime;

    /**
     * 用户登录
     * @param userNo
     * @param password
     * @return
     */
    @Override
    public UserEO login(String userNo, String password) {
        if (StringUtils.isBlank(userNo)){
            throw new BusinessException(ResponseCodes.EmptyInfo,"工号");
        }
        if (StringUtils.isBlank(password)){
            throw new BusinessException(ResponseCodes.EmptyInfo,"密码");
        }

        //从数据库根据工号获取用户
        Example example = new Example(UserEO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userNo",userNo);
        UserEO userEO = userEOMapper.selectOneByExample(example);

        if (userEO ==null){
            throw new BusinessException(ResponseCodes.NotExist,"该用户");
        }
        String encyptPassword = encyptPassword(password, userNo);
        if (!encyptPassword.equals(userEO.getUserPassword())){
            throw new BusinessException(ResponseCodes.InvalidPassword);
        }
        //过滤password信息
        userEO.setUserPassword(null);

        if(StringUtils.isNotBlank(userEO.getUserToken()) && userEO.getLastTime() != null){
            long time = System.currentTimeMillis() - userEO.getLastTime().getTime();
            if(time < loginExpireTime * 1000){
                LOGGER.info("用户已经登录" + userEO.toString());
                return userEO;
            }
        }

        //生成token，并保存到cookie中
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.SERVICE_REQUEST_HEADER_UID, userEO.getId().toString());
        String token = CookieUtils.generateCookie(jsonObject);

        //保存token到数据库
        userEO.setUserToken(token);
        userEO.setLastTime(new Date());
        this.modifyUser(userEO);
        return userEO;
    }

    /**
     * 修改用户信息
     * @param userEO 用户信息
     */
    @Override
    public void modifyUser(UserEO userEO) {
        userEOMapper.updateByPrimaryKeySelective(userEO);
    }

    /**
     * 加密密码
     * @param clearTextPwd
     * @param userLoginName
     * @return
     */
    private String encyptPassword(String clearTextPwd, String userLoginName) {
        return UrlBase64.encoded(Digests.sha1(clearTextPwd.getBytes(),
                userLoginName.getBytes()));
    }

    /**
     * 添加新用户
     * @param userRequest 用户注册信息
     */
    @Override
    public UserEO addUser(UserRequest userRequest) {
        if (userRequest == null){
            throw new BusinessException(ResponseCodes.EmptyInfo,"参数");
        }
        if (StringUtils.isEmpty(userRequest.getUserNo())){
            throw new BusinessException(ResponseCodes.EmptyInfo,"工号");
        }
        if (StringUtils.isEmpty(userRequest.getUserPassword())){
            throw new BusinessException(ResponseCodes.EmptyInfo,"密码");
        }

        UserEO userEO = BeanMapper.map(userRequest, UserEO.class);

        //加密密码
        String encyptPassword = encyptPassword(userRequest.getUserPassword(), userRequest.getUserNo());
        userEO.setUserPassword(encyptPassword);
        userEO.setCreateTime(new Date());
        userEOMapper.insert(userEO);

        //生成token，默认登录状态
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.SERVICE_REQUEST_HEADER_UID, userEO.getId().toString());
        String token = CookieUtils.generateCookie(jsonObject);

        //保存token到数据库
        userEO.setUserToken(token);
        userEO.setLastTime(new Date());
        this.modifyUser(userEO);
        userEO.setUserPassword(null);
        return userEO;
    }

    /**
     * 根据token获取登录用户
     * @param userToken 用户登录token
     * @param userId userId
     */
    @Override
    public UserEO userInfo(String userToken, Long userId) {
        if (StringUtils.isBlank(userToken)){
            throw new BusinessException(ResponseCodes.EmptyInfo,"token");
        }
        if (userId == null){
            throw new BusinessException(ResponseCodes.EmptyInfo,"用户id");
        }

        //生成token，并保存到cookie中
        JSONObject jsonObject = CookieUtils.cookieToObject(userToken);
        if (jsonObject != null) {
            String uidStr = (String)(jsonObject.get(CommonConstants.SERVICE_REQUEST_HEADER_UID));
            long uid = Long.parseLong(uidStr);
            if(uid != userId.longValue()){
                throw new BusinessException(ResponseCodes.UserNotLogin);
            }

            //根据主键获取用户
            UserEO userEO = userEOMapper.selectByPrimaryKey(uid);
            if(userEO == null){
                throw new BusinessException(ResponseCodes.UserNotLogin);
            }

            if(StringUtils.equals(userToken, userEO.getUserToken()) && uid == userEO.getId().longValue() && userEO.getLastTime() != null){
                long time = System.currentTimeMillis() - userEO.getLastTime().getTime();
                if(time < loginExpireTime * 1000){
                    LOGGER.info("用户登录有效：" + userEO.toString());
                    userEO.setUserPassword(null);
                    return userEO;
                }
            }
        }
        throw new BusinessException(ResponseCodes.UserNotLogin);
    }

}
