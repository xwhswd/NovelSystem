package com.iweb.manage.pojo;

import lombok.Data;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String create_time;
    private String balance;
}
