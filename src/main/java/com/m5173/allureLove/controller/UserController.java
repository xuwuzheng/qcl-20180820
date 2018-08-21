package com.m5173.allureLove.controller;

import com.alibaba.fastjson.JSONObject;
import com.m5173.allureLove.common.base.BaseController;
import com.m5173.allureLove.common.base.BaseResponse;
import com.m5173.allureLove.common.base.IServiceResponse;
import com.m5173.allureLove.common.constants.CommonConstants;
import com.m5173.allureLove.common.util.CookieUtils;
import com.m5173.allureLove.model.eo.UserEO;
import com.m5173.allureLove.model.request.UserRequest;
import com.m5173.allureLove.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
 * @author lvshuyan
 * @date 2018/5/11
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户相关API", description = "用户相关API")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 用户登录验证
     * @param userRequest 登录手机号和密码
     * @return
     */
    @PostMapping("login")
    @ApiOperation(value="用户登录验证接口", notes="用户登录验证接口")
    public IServiceResponse login(@RequestBody UserRequest userRequest){
        LOGGER.info("用户登录：userPhone：" + userRequest.getUserPhone() + ";userPassword:" + userRequest.getUserPassword());
        UserEO userEO = userService.login(userRequest.getUserPhone(), userRequest.getUserPassword());

        BaseResponse<UserEO> baseResponse = new BaseResponse();
        baseResponse.setData(userEO);
        LOGGER.info("用户登录验证成功" + userEO.toString());
        return baseResponse;
    }

    /**
     * 用户退出
     * @param userRequest 用户登录token
     * @return
     */
    @PostMapping("logout")
    @ApiOperation(value="用户退出接口", notes="用户退出接口")
    public IServiceResponse logout(@RequestBody UserRequest userRequest) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        if (StringUtils.isNotBlank(userRequest.getUserToken())) {
            // 失效Cookie
//            CookieUtils.deletePubCookie(request, response);

            JSONObject jsonObject = CookieUtils.cookieToObject(userRequest.getUserToken());
            if (jsonObject != null) {
                String uidStr = (String)(jsonObject.get(CommonConstants.SERVICE_REQUEST_HEADER_UID));
                long uid = Long.parseLong(uidStr);

                //保存token到数据库
                UserEO userEO = new UserEO();
                userEO.setId(uid);
                userEO.setUserToken("");
                userService.modifyUser(userEO);
            }
        }
        LOGGER.info("用户退出成功");
        return baseResponse;
    }

    /**
     * 用户注册
     * @param userRequest
     * @return
     */
    @PostMapping("addUser")
    @ApiOperation(value="添加用户接口", notes="添加用户接口")
    public IServiceResponse addUser(@RequestBody UserRequest userRequest){
        UserEO userEO = userService.addUser(userRequest);

        BaseResponse<UserEO> baseResponse = new BaseResponse();
        baseResponse.setData(userEO);
        LOGGER.info("注册用户成功");
        return baseResponse;
    }

    /**
     * 获取用户详情
     * @param userRequest userToken和userId
     * @return userId
     */
    @PostMapping("userInfo")
    @ApiOperation(value="获取用户详情", notes="获取用户详情")
    public IServiceResponse userInfo(@RequestBody UserRequest userRequest){
        UserEO userEO = userService.userInfo(userRequest.getUserToken());

        BaseResponse<UserEO> baseResponse = new BaseResponse();
        baseResponse.setData(userEO);
        return baseResponse;
    }


}
