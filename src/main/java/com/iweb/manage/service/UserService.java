package com.iweb.manage.service;

import com.iweb.manage.pojo.User;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public interface UserService {
    /**
     * 登录接口,对密码进行加密
     * @param user
     */
    User login(User user);

    /**
     * 用户姓名重复判断接口
     * @param username
     * @return
     */
    boolean verifyUsername(String username);

    /**
     * 用户注册接口
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 更新用户信息接口
     * @param user
     * @return
     */
    boolean updateUser(User user);
}
