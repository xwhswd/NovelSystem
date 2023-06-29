package com.iweb.manage.service.impl;

import com.iweb.manage.dao.CategoryBookDao;
import com.iweb.manage.dao.impl.CategoryBookDaoImpl;
import com.iweb.manage.pojo.Category;
import com.iweb.manage.pojo.CategoryBook;
import com.iweb.manage.service.CategoryBookService;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class CategoryBookServiceImpl implements CategoryBookService {
    private CategoryBookDao categoryBookDao = new CategoryBookDaoImpl();

    @Override
    public boolean addCategoryBook(CategoryBook categoryBook) {
        return categoryBookDao.addCategoryBook(categoryBook);
    }

    @Override
    public boolean deleteCategoryBook(CategoryBook categoryBook) {
        return categoryBookDao.deleteCategoryBook(categoryBook);
    }

    @Override
    public boolean updateCategoryBook(CategoryBook categoryBook) {
        return categoryBookDao.updateCategoryBook(categoryBook);
    }

    @Override
    public List<Category> listAllCategory() {
        return categoryBookDao.listAllCategory();
    }

    @Override
    public List<Category> listByBook(String bookid) {
        return categoryBookDao.listByBook(bookid);
    }
}
