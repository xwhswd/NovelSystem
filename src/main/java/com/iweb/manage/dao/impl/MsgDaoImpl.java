package com.iweb.manage.dao.impl;

import com.iweb.manage.dao.MsgDao;
import com.iweb.manage.pojo.Msg;
import com.iweb.manage.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class MsgDaoImpl implements MsgDao {
    QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public List<Msg> listMsg() {
        String sql = "select * from msg";
        try{
            List<Msg> query = qr.query(sql, new BeanListHandler<>(Msg.class));
            return query;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Msg getNewestMsg() {
        String sql = "select * from msg limit (select count(*) from msg),1";
        try{
            Msg query = qr.query(sql, new BeanHandler<>(Msg.class));
            return query;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
