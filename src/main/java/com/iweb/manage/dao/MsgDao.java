package com.iweb.manage.dao;

import com.iweb.manage.pojo.Msg;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public interface MsgDao {
    /**
     * 显示所有公告
     * @return
     */
    List<Msg> listMsg();

    /**
     * 获取最新公告
     * @return
     */
    Msg getNewestMsg();
}
