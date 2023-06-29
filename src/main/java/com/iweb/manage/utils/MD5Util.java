package com.iweb.manage.utils;

import java.security.MessageDigest;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class MD5Util {
    public static String getMD5(String password){
        StringBuffer buffer = new StringBuffer();
        try{
            //获取MD5的信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            //对密码进行信息摘要处理,得到一个字节数据
            byte[] result = digest.digest(password.getBytes());
            //将信息摘要处理之后的字节数组中的每一个字节和0xff进行与运算
            for(byte b:result){
                //按位与运算,加盐(salt)
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length()==1){
                    buffer.append(0);
                }
                buffer.append(str);
            }
            //返回md5加密的标准结果
            return buffer.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
