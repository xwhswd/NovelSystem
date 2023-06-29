package com.iweb.manage.dao.impl;

import com.iweb.manage.dao.BookShelfDao;
import com.iweb.manage.pojo.BookShelf;
import com.iweb.manage.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class BookShelfDaoImpl implements BookShelfDao {
    QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public List<BookShelf> listShelf(String uid) {
        String sql = "select * from bookshelf where uid=?";
        try{
            List<BookShelf> query = qr.query(sql, new BeanListHandler<>(BookShelf.class), uid);
            return query;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int count(String uid){
        String sql = "select count(*) from bookshelf where uid=?";
        try{
            Number number = (Number) qr.query(sql, new ScalarHandler<>(), uid);
            return number.intValue();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public boolean addBookShelf(BookShelf bookShelf) {
        String sql = "insert into bookshelf(uid,book_id,chapter_id,read_time) values(?,?,?,?)";
        try{
            int update = qr.update(sql, bookShelf.getUid(), bookShelf.getBook_id(), bookShelf.getChapter_id(), bookShelf.getRead_time());
            return update>0;
        }catch (SQLException e){
            return false;
        }
    }

    @Override
    public boolean deleteBookShelf(BookShelf bookShelf) {
        String sql = "delete from bookshelf where uid=? and book_id=?";
        try{
            int update = qr.update(sql, bookShelf.getUid(), bookShelf.getBook_id());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBookShelf(BookShelf bookShelf) {
        String sql = "update bookshelf set chapter_id=?,read_time=? where uid=? and book_id=?";
        try{
            int update = qr.update(sql, bookShelf.getChapter_id(),bookShelf.getRead_time(),bookShelf.getUid(), bookShelf.getBook_id());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
