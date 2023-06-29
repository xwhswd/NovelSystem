package com.iweb.manage.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class FormBeanUtil {
    //对应的是req.getParameterMap
    //体现了IOC思想:控制反转,将创建对象的权力由开发者交给容器(Spring或其他工具类)
    public static<T> T formToBean(Map<String,String[]> map, Class<T> tClass){
        T t = null;
        try{
            //借助反射完成实体类对象的创建
            //t = tClass.newInstance();
            Constructor<T> c = tClass.getConstructor();
            t = c.newInstance();
            //借助bean工具类将map中的属性值注入到bean对象中
            BeanUtils.populate(t,map);
            //
        }catch (Exception e){

        }
        return t;
    }
}
