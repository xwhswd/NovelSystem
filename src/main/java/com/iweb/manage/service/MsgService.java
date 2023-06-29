package com.iweb.manage.service;

import com.iweb.manage.pojo.Msg;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public interface MsgService {
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
