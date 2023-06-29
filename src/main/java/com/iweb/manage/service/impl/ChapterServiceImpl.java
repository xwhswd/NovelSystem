package com.iweb.manage.service.impl;

import com.iweb.manage.dao.BookDao;
import com.iweb.manage.dao.ChapterBookDao;
import com.iweb.manage.dao.ChapterDao;
import com.iweb.manage.dao.impl.BookDaoImpl;
import com.iweb.manage.dao.impl.ChapterBookDaoImpl;
import com.iweb.manage.dao.impl.ChapterDaoImpl;
import com.iweb.manage.pojo.Chapter;
import com.iweb.manage.pojo.ChapterBook;
import com.iweb.manage.service.ChapterService;
import com.iweb.manage.utils.UUIDUtil;

import java.util.Date;
import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class ChapterServiceImpl implements ChapterService {
    private ChapterBookDao chapterBookDao = new ChapterBookDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    private ChapterDao chapterDao = new ChapterDaoImpl();
    @Override
    public boolean addChapter(Chapter chapter,String bookid) {
        String uuid = UUIDUtil.uuid();
        chapter.setId(uuid);
        boolean b = chapterDao.addChapter(chapter);
        if (b){
            ChapterBook chapterBook = new ChapterBook();
            chapterBook.setBookid(bookid);
            chapterBook.setChapterid(uuid);
            bookDao.updateCount(bookid);
            return chapterBookDao.addChapterBook(chapterBook);
        }
        return false;
    }

    @Override
    public boolean deleteChapter(Chapter chapter) {
        boolean b = chapterDao.deleteChapter(chapter);
        if (b){
            ChapterBook chapterBook = new ChapterBook();
            chapterBook.setChapterid(chapter.getId());
            bookDao.updateCount(chapterBookDao.getBookId(chapter.getId()));
            return chapterBookDao.deleteChapterBook(chapterBook);
        }
        return false;
    }

    @Override
    public boolean updateChapter(Chapter chapter) {
        String bookId = chapterBookDao.getBookId(chapter.getId());
        bookDao.updateCount(bookId);
        return chapterDao.updateChapter(chapter);
    }

    @Override
    public List<Chapter> listByBook(String bookid) {
        List<Chapter> chapters = chapterDao.listByBook(bookid);
        if (chapters!=null){
            for (Chapter c:chapters){
                c.setContent("");
            }
        }
        return chapters;
    }

    @Override
    public Chapter getNewestChapter(String bookid){
        Chapter newestChapter = chapterDao.getNewestChapter(bookid);
        String uploadTime = newestChapter.getUpload_time();
        newestChapter.setUpload_time(uploadTime.split("\\.")[0]);
        return newestChapter;
    }

    @Override
    public Chapter getChapterById(String chapterId,String num){
        Chapter chapter=null;
        if (num==null) {
            chapter = chapterDao.getChapterById(chapterId);
        }else {
            chapter = chapterDao.getChapterByNum(chapterBookDao.getBookId(chapterId),num);
            if (chapter==null){
                Chapter chapter1 = new Chapter();
                chapter1.setId("*");
                chapter1.setTitle("等待上傳中");
                chapter1.setUpload_time(new Date().toString());
                chapter1.setNum(num);
                chapter1.setContent("已經是最後一章了,請等待上傳");
                return chapter1;
            }
        }
        if (chapter==null){
            chapter = chapterDao.getChapterByNum(chapterBookDao.getBookId(chapterId),num);
            if (chapter==null){
                Chapter chapter1 = new Chapter();
                chapter1.setId("*");
                chapter1.setTitle(null);
                chapter1.setUpload_time(new Date().toString());
                chapter1.setNum(num);
                chapter1.setContent("");
                return chapter1;
            }
        }
        String uploadTime = chapter.getUpload_time();
        chapter.setUpload_time(uploadTime.split("\\.")[0]);
        return chapter;
    }
}
