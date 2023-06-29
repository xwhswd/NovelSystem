package com.iweb.manage.service;

import com.iweb.manage.pojo.BookShelf;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public interface BookShelfService {
    /**
     *获取用户的所有书架书籍
     * @param uid
     * @return
     */
    List<BookShelf> listShelf(String uid,String type);

    /**
     * 统计书架藏书
     * @param uid
     * @return
     */
    int count(String uid);

    /**
     * 新增书架
     * @param bookShelf
     * @return 书架新增情况
     */
    boolean addBookShelf(BookShelf bookShelf);

    /**
     * 根据id删除书架
     * @param bookShelf
     * @return 书架删除情况
     */
    boolean deleteBookShelf(BookShelf bookShelf);

    /**
     * 根据id更新书架
     * @param bookShelf
     * @return 书架更新情况
     */
    boolean updateBookShelf(BookShelf bookShelf);
}
