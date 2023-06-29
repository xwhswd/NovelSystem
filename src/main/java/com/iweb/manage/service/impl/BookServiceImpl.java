package com.iweb.manage.service.impl;

import com.iweb.manage.dao.BookDao;
import com.iweb.manage.dao.impl.BookDaoImpl;
import com.iweb.manage.pojo.Book;
import com.iweb.manage.pojo.Chapter;
import com.iweb.manage.service.BookService;
import com.iweb.manage.utils.MD5Util;
import com.iweb.manage.utils.UUIDUtil;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public List<Book> bookList(int pageIndex, int pageSize, String bookname, String booktypes,String ordertype,
                               String tag1,String tag2,String tag3,String tag4) {
        return bookDao.bookList(pageIndex,pageSize,bookname,booktypes,ordertype,tag1,tag2,tag3,tag4);
    }

    @Override
    public boolean addBook(Book book) {
        book.setId(UUIDUtil.uuid());
        return bookDao.addBook(book);
    }

    @Override
    public boolean deleteBook(Book book) {
        return bookDao.deleteBook(book);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public List<Book> listFree(int pageIndex, int pageSize) {
        return bookDao.listFree(pageIndex,pageSize);
    }

    @Override
    public Book getBookById(String id) {
        return bookDao.getBookById(id);
    }

}
