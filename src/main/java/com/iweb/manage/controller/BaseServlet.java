package com.iweb.manage.controller;

import cn.hutool.extra.template.engine.thymeleaf.ThymeleafEngine;
import com.iweb.manage.service.*;
import com.iweb.manage.service.impl.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
public class BaseServlet extends HttpServlet {
    protected BookService bookService = new BookServiceImpl();
    protected ChapterService chapterService = new ChapterServiceImpl();
    protected CategoryBookService categoryBookService = new CategoryBookServiceImpl();
    protected BookShelfService bookShelfService = new BookShelfServiceImpl();
    protected RecommendService recommendService = new RecommendServiceImpl();
    protected UserService userService = new UserServiceImpl();
    protected MsgService msgService = new MsgServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String method_name = req.getParameter("method");
        try{
            Method method = this.getClass().getMethod(method_name,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        }catch (Exception e){
            System.out.println("方法名调用错误,请检查代码");
            resp.sendRedirect("404.html");
            e.printStackTrace();
        }
    }

}
