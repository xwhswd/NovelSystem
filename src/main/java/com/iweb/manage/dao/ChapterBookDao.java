package com.iweb.manage.dao;

import com.iweb.manage.pojo.ChapterBook;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public interface ChapterBookDao {
    /**
     * 新增
     * @param chapterBook
     * @return
     */
    boolean addChapterBook(ChapterBook chapterBook);

    /**
     * 删除
     * @param chapterBook
     * @return
     */
    boolean deleteChapterBook(ChapterBook chapterBook);

    /**
     * 获取书籍id
     * @param chapterid
     * @return
     */
    String getBookId(String chapterid);
}
