package com.iweb.manage.dao.impl;

import com.iweb.manage.dao.RecommendDao;
import com.iweb.manage.pojo.Msg;
import com.iweb.manage.pojo.Recommend;
import com.iweb.manage.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class RecommendDaoImpl implements RecommendDao {
    QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public List<Recommend> listRecommend() {
        String sql = "select * from recommend";
        try{
            List<Recommend> query = qr.query(sql, new BeanListHandler<>(Recommend.class));
            return query;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
