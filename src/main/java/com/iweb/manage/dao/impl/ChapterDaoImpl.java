package com.iweb.manage.dao.impl;

import com.iweb.manage.dao.ChapterDao;
import com.iweb.manage.pojo.Chapter;
import com.iweb.manage.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class ChapterDaoImpl implements ChapterDao {
    QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public boolean addChapter(Chapter chapter) {
        String sql = "insert into chapter(id,num,title,content,upload_time) values (?,?,?,?,?,?)";
        try{
            int update = qr.update(sql, chapter.getId(), chapter.getNum(), chapter.getTitle(), chapter.getContent(),
                    chapter.getUpload_time());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteChapter(Chapter chapter) {
        String sql = "delete chapter where id = ?";
        try{
            int update = qr.update(sql, chapter.getId());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateChapter(Chapter chapter) {
        String sql = "update chapter set num=?,title=?,content=?,upload_time=? where id = ?";
        try{
            int update = qr.update(sql, chapter.getNum(),chapter.getTitle(),chapter.getContent(),
                    chapter.getUpload_time(),chapter.getId());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Chapter> listByBook(String bookid) {
        String sql = "select * from chapter where id in (select cid from chapter_book where bid=?)";
        try {
            List<Chapter> query = qr.query(sql, new BeanListHandler<>(Chapter.class), bookid);
            return  query;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Chapter getNewestChapter(String bookId){
        String sql = "select * from chapter where id in (select cid from chapter_book where bid=?) order by upload_time desc LIMIT 0,1";
        try {
            Chapter query = qr.query(sql, new BeanHandler<>(Chapter.class), bookId);
            return  query;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Chapter getChapterById(String captchaId){
        String sql = "select * from chapter where id=?";
        try {
            Chapter query = qr.query(sql, new BeanHandler<>(Chapter.class), captchaId);
            return  query;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Chapter getChapterByNum(String bookid,String num){
        String sql = "select * from chapter where id in " +
                "(select cid from chapter_book where bid=?) and num=?";
        try {
            Chapter query = qr.query(sql, new BeanHandler<>(Chapter.class),bookid,num);
            return  query;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
