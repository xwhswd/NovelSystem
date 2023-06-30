package com.iweb.manage.dao.impl;

import com.iweb.manage.dao.BookDao;
import com.iweb.manage.dao.ChapterBookDao;
import com.iweb.manage.dao.ChapterDao;
import com.iweb.manage.pojo.Book;
import com.iweb.manage.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class BookDaoImpl implements BookDao {
    QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public List<Book> bookList(int pageIndex, int pageSize, String bookname, String booktypes,String ordertype,
                               String tag1,String tag2,String tag3,String tag4) {
        List<Book> bookList = null;
        int a = pageIndex*pageSize;
        Comparator<Book> comparator=null;
        String sql = "select distinct b.id,b.bookname,b.writer," +
                "b.comments,b.state,b.cover,b.is_free,b.click,b.num " +
                " from book b left join " +
                "(select cb.book_id,c.category_name as type " +
                "from category_book  cb left join category c on cb.category_id=c.id) cbs " +
                "on b.id=cbs.book_id " +
                "where 1=1";
        try{
            List<Object> params = new ArrayList<>();
            if (bookname!=null&&!bookname.equals("")){
                params.add("%"+bookname+"%");
                sql = sql + " and bookname like ?";
            }
            if (booktypes!=null){
                sql = sql + " and type =\'"+booktypes+"\'";
            }
            if (!tag1.equals("1")){
                if (tag1.equals("30")){
                    sql = sql+" and num<300000";
                }else if(tag1.equals("100")){
                    sql = sql+" and num<1000000 and num>=50000";
                }else {
                    sql = sql+" and num<2000000 and num>=1000000";
                }
            }
            if (!tag2.equals("1")){
                if (tag2.equals("over")){
                    sql = sql +" and state='完结'";
                }else if(tag2.equals("out")){
                    sql = sql +" and state='出版'";
                }else {
                    sql = sql +" and state='连载'";
                }
            }
            if (!tag3.equals("1")){
                if (tag3.equals("free")){
                    sql = sql +" and is_free='1'";
                }else {
                    sql = sql +" and is_free='0'";
                }
            }
            if (!tag4.equals("1")){ 
                if (tag4.equals("click")){
                    sql= sql + " order by click desc";
                }else if (tag4.equals("num")) {
                    sql= sql + " order by num desc";
                }else {
                    //放在后面处理
                    comparator = new Comparator<Book>() {
                        private ChapterDao chapterDao = new ChapterDaoImpl();
                        @Override
                        public int compare(Book o1, Book o2) {
                            String uploadTime = chapterDao.getNewestChapter(o1.getId()).getUpload_time();
                            String uploadTime1 = chapterDao.getNewestChapter(o2.getId()).getUpload_time();
                            return uploadTime.compareTo(uploadTime1);
                        }
                    };
                }

            }
//            if (ordertype!=null){
//                sql= sql + " order by "+ordertype+" desc";
//            }
            sql = sql + " limit "+a+","+pageSize;
            if (params.size()==0){
                bookList = qr.query(sql,new BeanListHandler<>(Book.class));
            }else {
                bookList = qr.query(sql,new BeanListHandler<>(Book.class),params.toArray());
            }
            if (tag4.equals("time")){
                Collections.sort(bookList,comparator);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public boolean addBook(Book book) {
        String sql = "insert into book(id,bookname,writer,comments,state,cover,is_free,click,num) values" +
                "(?,?,?,?,?,?,?,?,0)";
        try{
            int update = qr.update(sql, book.getId(), book.getBookname(), book.getWriter(), book.getComments(),
                    book.getState(), book.getCover(), book.getIs_free(), book.getClick());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBook(Book book) {
        String sql = "delete from book where id = ?";
        try{
            int update = qr.update(sql, book.getId());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBook(Book book) {
        String sql = "update book set bookname=?,comments=?,state=?,cover=?,is_free=?,click=? where id=?";
        try{
            int update = qr.update(sql, book.getBookname(),  book.getComments(),
                    book.getState(), book.getCover(), book.getIs_free(), book.getClick(),book.getId());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCount(String bookid){
        String sql = "SELECT sum(LENGTH(content) - LENGTH(REPLACE(content, ' ', '')) + 1) FROM chapter c left join chapter_book cb" +
                " on c.id=cb.cid where cb.bid=?";
        try{
            Number number = (Number) qr.query(sql,new ScalarHandler<>(),bookid);
            String sql2 = "update book set num=? where id = ?";
            int update = qr.update(sql2, number.intValue(), bookid);
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> listFree(int pageIndex, int pageSize) {
        List<Book> bookList = null;
        int a = pageIndex*pageSize;
        String sql = "select distinct b.id,b.bookname,b.writer," +
                "b.comments,b.state,b.cover,b.is_free,b.click,b.num " +
                " from book b left join " +
                "(select cb.book_id,c.category_name as type " +
                "from category_book  cb left join category c on cb.category_id=category_id) cbs " +
                "on b.id=cbs.book_id " +
                "where is_free='1'";
        try{
            List<String> params = new ArrayList<>();
            sql = sql + " limit "+a+","+pageSize;
            bookList = qr.query(sql,new BeanListHandler<>(Book.class),params.toArray());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public Book getBookById(String id) {
        String sql = "SELECT * from book where id=?";
        try{
            Book book =  qr.query(sql,new BeanHandler<>(Book.class),id);
            return book;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
