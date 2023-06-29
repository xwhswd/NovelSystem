package com.iweb.manage.pojo;

import lombok.Data;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/28
 */
@Data
public class UserMessage {
    private String uid;
    private String username;
    private String percent;
    private String collect;
    private String balance;
    private String register_time;
    private String email;
    private String phone;
}
