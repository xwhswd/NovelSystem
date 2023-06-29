package com.iweb.manage.service;

import com.iweb.manage.pojo.Book;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public interface BookService {
    /**
     * 分页展示小说
     * @param pageIndex 起始
     * @param pageSize 页大小
     * @param bookname 书名
     * @param booktypes 分类
     * @return
     */
    List<Book> bookList(int pageIndex, int pageSize, String bookname, String booktypes,String ordertype,
                        String tag1,String tag2,String tag3,String tag4);

    /**
     * 新增书籍
     * @param book
     * @return 书籍增加情况
     */
    boolean addBook(Book book);

    /**
     * 根据删除书籍
     * @param book
     * @return 书籍删除情况
     */
    boolean deleteBook(Book book);

    /**
     * 根据id更新书籍
     * @param book
     * @return 书籍更新情况
     */
    boolean updateBook(Book book);

    /**
     * 列出免費書記
     * @return
     */
    List<Book> listFree(int pageIndex, int pageSize);

    /**
     * 根据id获取书籍
     * @param id
     * @return
     */
    Book getBookById(String id);
}
