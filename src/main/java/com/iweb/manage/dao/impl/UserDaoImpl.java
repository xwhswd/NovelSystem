package com.iweb.manage.dao.impl;

import com.iweb.manage.dao.UserDao;
import com.iweb.manage.pojo.User;
import com.iweb.manage.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class UserDaoImpl implements UserDao {
    QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public User login(User user) {
        String sql = "select * from user where username=? and password=?";
        try{
            User queryUser = qr.query(sql, new BeanHandler<>(User.class), user.getUsername(), user.getPassword());
            return queryUser;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean verifyUsername(String username) {
        String sql = "select count(*) from user where username=?";
        try{
            Number number = (Number) qr.query(sql, new ScalarHandler<>(), username);
            return number.intValue()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        String sql = "insert into user(id,username,password,create_time,balance) values (?,?,?,?,?)";
        try{
            int update = qr.update(sql, user.getId(), user.getUsername(), user.getPassword(), user.getCreate_time(), user.getBalance());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "update user set password=?,email=?,phone=?,balance=? where id=?";
        try{
            int update = qr.update(sql, user.getPassword(), user.getEmail(),user.getPhone(),user.getBalance(),user.getId());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
