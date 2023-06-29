package com.iweb.manage.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class DruidUtil {
    private static DataSource dataSource;
    static {
        //获取配置文件对应的输入流
        InputStream inputStream = DruidUtil.class.getClassLoader().
                getResourceAsStream("db.properties");
        //实例化用于读取配置文件的对象
        Properties p = new Properties();
        try {
            //使用配置文件对象加载文件流
            p.load(inputStream);
            //基于配置文件对象,实例化数据源
            dataSource = DruidDataSourceFactory.createDataSource(p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }
}
