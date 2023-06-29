package com.iweb.manage.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class DateUtil {
    public static  String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }
}
