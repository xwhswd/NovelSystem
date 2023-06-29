package com.iweb.manage.service.impl;

import com.iweb.manage.dao.MsgDao;
import com.iweb.manage.dao.impl.MsgDaoImpl;
import com.iweb.manage.pojo.Msg;
import com.iweb.manage.service.MsgService;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class MsgServiceImpl implements MsgService {
    private MsgDao msgDao = new MsgDaoImpl();
    @Override
    public List<Msg> listMsg() {
        return msgDao.listMsg();
    }

    @Override
    public Msg getNewestMsg() {
        return msgDao.getNewestMsg();
    }
}
