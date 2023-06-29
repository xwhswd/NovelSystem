package com.iweb.manage.service;

import com.iweb.manage.pojo.Chapter;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public interface ChapterService {
    /**
     * 新增章节信息
     * @param chapter
     * @return 新增情况
     */
    boolean addChapter(Chapter chapter,String bookid);

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

    Chapter getNewestChapter(String bookid);

    Chapter getChapterById(String chapterId,String num);
}
