package com.iweb.manage.service.impl;

import com.iweb.manage.dao.UserDao;
import com.iweb.manage.dao.impl.UserDaoImpl;
import com.iweb.manage.pojo.User;
import com.iweb.manage.service.UserService;
import com.iweb.manage.utils.DateUtil;
import com.iweb.manage.utils.MD5Util;
import com.iweb.manage.utils.UUIDUtil;

import java.util.Date;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User login(User user) {
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        return userDao.login(user);
    }

    @Override
    public boolean verifyUsername(String username) {
        return userDao.verifyUsername(username);
    }

    @Override
    public boolean register(User user) {
        user.setId(UUIDUtil.uuid());
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        user.setCreate_time(DateUtil.dateToString(new Date()));
        user.setBalance("0.0");
        return userDao.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }


}
