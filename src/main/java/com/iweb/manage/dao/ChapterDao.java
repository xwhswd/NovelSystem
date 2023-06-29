package com.iweb.manage.dao;

import com.iweb.manage.pojo.Chapter;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public interface ChapterDao {
    /**
     * 新增章节信息
     * @param chapter
     * @return 新增情况
     */
    boolean addChapter(Chapter chapter);

    /**
     * 删除章节信息
     * @param chapter
     * @return 删除情况
     */
    boolean deleteChapter(Chapter chapter);

    /**
     * 根据id删除章节信息
     * @param chapter
     * @return
     */
    boolean updateChapter(Chapter chapter);

    /**
     * 根据bookid展示小说章节
     * @param bookid
     * @return
     */
    List<Chapter> listByBook(String bookid);

    /**
     * 獲取最新章節信息
     * @param bookId
     * @return
     */
    Chapter getNewestChapter(String bookId);

    /**
     * 獲取章節信息
     * @param captchaId
     * @return
     */
    Chapter getChapterById(String captchaId);

    /**
     * 根據書和編號取章節書記
     * @param bookid
     * @param num
     * @return
     */
    Chapter getChapterByNum(String bookid, String num);
}
