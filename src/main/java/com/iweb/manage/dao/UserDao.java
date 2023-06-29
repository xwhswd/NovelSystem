package com.iweb.manage.dao;

import com.iweb.manage.pojo.User;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public interface UserDao {
    /**
     * 登录校验
     * @return 返回校验信息
     */
    User login(User user);

    /**
     * 校验是否有重复用户名
     * @param username
     * @return 返回校验信息
     */
    boolean verifyUsername(String username);

    /**
     * 新增用户
     * @param user
     * @return 校验信息
     */
    boolean addUser(User user);

    /**
     * 根据id更新用户信息
     * @param user
     * @return 校验信息
     */
    boolean updateUser(User user);
}
