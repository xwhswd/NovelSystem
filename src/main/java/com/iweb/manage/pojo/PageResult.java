package com.iweb.manage.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/25
 */
@Data
public class PageResult <T>{
    /**
     * 满足条件的记录数
     */
    private int total;
    /**
     * 每一页的数据
     */
    private List<T> data;
}
