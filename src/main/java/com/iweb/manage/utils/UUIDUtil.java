package com.iweb.manage.utils;

import java.util.UUID;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().
                toString().
                replace("-","");
    }
}
