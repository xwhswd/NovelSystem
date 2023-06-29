package com.iweb.manage.dao.impl;

import com.iweb.manage.dao.ChapterBookDao;
import com.iweb.manage.pojo.ChapterBook;
import com.iweb.manage.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class ChapterBookDaoImpl implements ChapterBookDao {
    QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
    @Override
    public boolean addChapterBook(ChapterBook chapterBook) {
        String sql = "insert into chapter_book(cid,bid) values (?,?)";
        try {
            int update = qr.update(sql, chapterBook.getChapterid(), chapterBook.getBookid());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteChapterBook(ChapterBook chapterBook) {
        String sql = "delete from chapter_book where bid=? and cid=?";
        try {
            int update = qr.update(sql, chapterBook.getBookid(), chapterBook.getChapterid());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public String getBookId(String chapterid){
        String sql = "select bid from chapter_book where cid=?";
        try {
            String chapterId = (String) qr.query(sql, new ScalarHandler<>(), chapterid);
            return chapterId;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
