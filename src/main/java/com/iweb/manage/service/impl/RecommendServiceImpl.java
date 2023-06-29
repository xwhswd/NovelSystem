package com.iweb.manage.service.impl;

import com.iweb.manage.dao.RecommendDao;
import com.iweb.manage.dao.UserDao;
import com.iweb.manage.dao.impl.RecommendDaoImpl;
import com.iweb.manage.pojo.Recommend;
import com.iweb.manage.service.RecommendService;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class RecommendServiceImpl implements RecommendService {
    RecommendDao recommendDao = new RecommendDaoImpl();
    @Override
    public List<Recommend> listRecommend() {
        return recommendDao.listRecommend();
    }
}
