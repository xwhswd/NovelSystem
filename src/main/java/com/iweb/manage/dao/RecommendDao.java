package com.iweb.manage.dao;

import com.iweb.manage.pojo.Recommend;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public interface RecommendDao {
    /**
     * 展示recommend
     * @return
     */
    List<Recommend> listRecommend();
}
