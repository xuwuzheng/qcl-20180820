package com.m5173.allureLove.service;


import com.m5173.allureLove.common.base.IBaseService;
import com.m5173.allureLove.model.eo.UserEO;
import com.m5173.allureLove.model.request.UserRequest;

/**
 * 后台用户业务层
 *
 * @author zhulf
 * @date 2018/4/25
 */
public interface IUserService extends IBaseService<UserEO> {

    /**
     * 用户登录
     * @param userNo 登录帐号
     * @param password 密码
     * @return 用户信息
     */
    UserEO login(String userNo, String password);

    /**
     * 修改用户信息
     * @param userEO 用户信息
     */
    public void modifyUser(UserEO userEO);

    /**
     * 添加新用户
     * @param userRequest 用户注册信息
     */
    UserEO addUser(UserRequest userRequest);

    /**
     * 根据token获取登录用户
     * @param userToken 用户登录token
     * @param userId userId
     */
    UserEO userInfo(String userToken, Long userId);
}
