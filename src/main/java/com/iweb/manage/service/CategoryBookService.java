package com.iweb.manage.service;

import com.iweb.manage.pojo.Category;
import com.iweb.manage.pojo.CategoryBook;

import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public interface CategoryBookService {
    /**
     * 对书籍新增分类
     * @param categoryBook
     * @return 新增结果
     */
    boolean addCategoryBook(CategoryBook categoryBook);

    /**
     * 删除该书籍的某些分类信息
     * @param categoryBook
     * @return 删除结果
     */
    boolean deleteCategoryBook(CategoryBook categoryBook);

    /**
     * 修改书籍的类别信息
     * @param categoryBook
     * @return 修改结果
     */
    boolean updateCategoryBook(CategoryBook categoryBook);

    /**
     * 展示所有分类信息
     * @return
     */
    List<Category> listAllCategory();

    /**
     * 根据书籍展示id
     * @param bookid
     * @return
     */
    List<Category> listByBook(String bookid);
}
