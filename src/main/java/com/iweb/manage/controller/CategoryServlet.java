package com.iweb.manage.controller;

import cn.hutool.json.JSONUtil;
import com.iweb.manage.pojo.Category;
import com.iweb.manage.pojo.ResultVo;
import com.iweb.manage.service.CategoryBookService;
import com.iweb.manage.service.impl.CategoryBookServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
@WebServlet("/category")
public class CategoryServlet extends BaseServlet{

    public void listCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Category> categories = categoryBookService.listAllCategory();
        ResultVo<List<Category>> resultVo = new ResultVo<>();
        resultVo.setOk(true);
        resultVo.setT(categories);
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }
}
