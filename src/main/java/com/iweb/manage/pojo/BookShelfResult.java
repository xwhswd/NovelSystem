package com.iweb.manage.pojo;

import lombok.Data;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/28
 */
@Data
public class BookShelfResult {
    String book_id;
    String book_name;
    String cover;
    String writer;
    String chapter_id;
    String chapter_state;
    String update_chapter;
}
