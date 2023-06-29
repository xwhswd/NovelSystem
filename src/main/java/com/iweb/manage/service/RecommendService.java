package com.iweb.manage.service;

import com.iweb.manage.pojo.Recommend;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public interface RecommendService {
    /**
     * 展示recommend
     * @return
     */
    List<Recommend> listRecommend();
}
