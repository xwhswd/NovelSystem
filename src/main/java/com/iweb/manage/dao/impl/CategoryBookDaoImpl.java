package com.iweb.manage.dao.impl;

import com.iweb.manage.dao.BookShelfDao;
import com.iweb.manage.dao.CategoryBookDao;
import com.iweb.manage.pojo.BookShelf;
import com.iweb.manage.pojo.Category;
import com.iweb.manage.pojo.CategoryBook;
import com.iweb.manage.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class CategoryBookDaoImpl implements CategoryBookDao{
    QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public boolean addCategoryBook(CategoryBook categoryBook) {
        String sql = "insert into category_book(category_id,book_id) values(?,?)";
        try{
            int update = qr.update(sql, categoryBook.getCategory_Id(), categoryBook.getBook_id());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCategoryBook(CategoryBook categoryBook) {
        String sql = "delete from category_book where book_id=? and category_id=?";
        try{
            int update = qr.update(sql, categoryBook.getBook_id(), categoryBook.getCategory_Id());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCategoryBook(CategoryBook categoryBook) {
        String sql = "update category_book set category_id=? where book_id=?";
        try{
            int update = qr.update(sql, categoryBook.getCategory_Id(), categoryBook.getBook_id());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Category> listAllCategory() {
        String sql = "select * from category";
        try{
            List<Category> query = qr.query(sql, new BeanListHandler<>(Category.class));
            return query;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> listByBook(String bookid) {
        String sql = "select category_id,category_name from category c left join category_book cb on c.id=cb.category_id where" +
                " cb.book_id=?";
        try{
            List<Category> query = qr.query(sql, new BeanListHandler<>(Category.class),bookid);
            return query;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}