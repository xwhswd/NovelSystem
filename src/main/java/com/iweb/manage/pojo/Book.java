package com.iweb.manage.pojo;

import lombok.Data;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
@Data
public class Book {
    private String id;
    private String bookname;
    private String writer;
    private String comments;
    private String state;
    private String cover;
    private String is_free;
    private String click;
    private String num;
}
