package com.iweb.manage.service.impl;

import com.iweb.manage.dao.BookDao;
import com.iweb.manage.dao.BookShelfDao;
import com.iweb.manage.dao.CategoryBookDao;
import com.iweb.manage.dao.ChapterDao;
import com.iweb.manage.dao.impl.BookDaoImpl;
import com.iweb.manage.dao.impl.BookShelfDaoImpl;
import com.iweb.manage.dao.impl.CategoryBookDaoImpl;
import com.iweb.manage.dao.impl.ChapterDaoImpl;
import com.iweb.manage.pojo.BookShelf;
import com.iweb.manage.pojo.Category;
import com.iweb.manage.pojo.Chapter;
import com.iweb.manage.service.BookShelfService;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class BookShelfServiceImpl implements BookShelfService {
    private BookShelfDao bookShelfDao = new BookShelfDaoImpl();
    private ChapterDao chapterDao = new ChapterDaoImpl();
    @Override
    public List<BookShelf> listShelf(String uid,String type) {
        List<BookShelf> bookShelves = bookShelfDao.listShelf(uid);
        if (bookShelves==null){
            return null;
        }
        Comparator<BookShelf> sortByUpdate = new Comparator<BookShelf>() {
            @Override
            public int compare(BookShelf o1, BookShelf o2) {
                Chapter newestChapter = chapterDao.getNewestChapter(o1.getBook_id());
                Chapter newestChapter1 = chapterDao.getNewestChapter(o2.getBook_id());
                return newestChapter.getUpload_time().compareTo(newestChapter1.getUpload_time());
            }
        };
        Comparator<BookShelf> sortByRead = new Comparator<BookShelf>() {
            @Override
            public int compare(BookShelf o1, BookShelf o2) {
                return o1.getRead_time().compareTo(o2.getRead_time());
            }
        };
        switch (type){
            case "update_time":
                Collections.sort(bookShelves,sortByUpdate);
                break;
            case "read_time":
                Collections.sort(bookShelves,sortByRead);
                break;
        }
        return bookShelves;
    }

    @Override
    public int count(String uid) {
        return bookShelfDao.count(uid);
    }

    @Override
    public boolean addBookShelf(BookShelf bookShelf) {
        return bookShelfDao.addBookShelf(bookShelf);
    }

    @Override
    public boolean deleteBookShelf(BookShelf bookShelf) {
        return bookShelfDao.deleteBookShelf(bookShelf);
    }

    @Override
    public boolean updateBookShelf(BookShelf bookShelf) {
        return bookShelfDao.updateBookShelf(bookShelf);
    }


}
