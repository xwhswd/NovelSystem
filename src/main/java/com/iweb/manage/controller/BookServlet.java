package com.iweb.manage.controller;

import cn.hutool.json.JSONUtil;
import com.iweb.manage.pojo.*;
import com.iweb.manage.utils.DateUtil;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
@WebServlet("/book")
public class BookServlet extends BaseServlet{

    public void searchAll(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String tag1 = req.getParameter("tag1");
        String tag2 = req.getParameter("tag2");
        String tag3 = req.getParameter("tag3");
        String tag4 = req.getParameter("tag4");
        String cid = req.getParameter("cid");
        if (cid.equals("*")){
            cid=null;
        }
        String msg = req.getParameter("msg");
        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        System.out.println(msg);
        List<Book> bookList = bookService.bookList(pageIndex, pageSize, msg, cid,null,
                tag1,tag2,tag3,tag4);
        resp.getWriter().write(JSONUtil.toJsonStr(bookList));
    }

    public void searchFree(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String count = req.getParameter("count");
        String type = req.getParameter("type");
        String state = req.getParameter("state");
        String is_free = req.getParameter("is_free");
        String order = req.getParameter("order_type");
        List<Book> bookList = bookService.listFree(pageIndex, pageSize);
        resp.getWriter().write(JSONUtil.toJsonStr(bookList));
    }

    public void getNewestChapter(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String bookid = req.getParameter("bookid");
        Chapter newestChapter = chapterService.getNewestChapter(bookid);
        resp.getWriter().write(JSONUtil.toJsonStr(newestChapter));
    }

    public void addShelf(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        ResultVo resultVo = new ResultVo();
        String bookid = req.getParameter("id");
        String num = req.getParameter("num");
        String cid = req.getParameter("cid");
        if (num==null){
            num = chapterService.getChapterById(cid,null).getNum();
        }
        BookShelf bookShelf = new BookShelf();
        bookShelf.setBook_id(bookid);
        if (req.getSession().getAttribute("user")!=null){
            bookShelf.setUid(((User)req.getSession().getAttribute("user")).getId());
        }else {
            if (bookService.getBookById(bookid).getIs_free().equals("1")){
                resultVo.setMess("免费无需登录");
                resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
            }
        }
        if (num.equals("0")){
            bookShelf.setRead_time(DateUtil.dateToString(new Date()));
            bookShelf.setChapter_id("*");
        }else {
            bookShelf.setRead_time(DateUtil.dateToString(new Date()));
            bookShelf.setChapter_id(cid);
        }
        if (bookShelfService.addBookShelf(bookShelf)){
            resultVo.setOk(true);
            resultVo.setMess("添加成功");
            Book bookById = bookService.getBookById(bookid);
            bookById.setClick(String.valueOf(Integer.parseInt(bookById.getClick())+1));
            bookService.updateBook(bookById);
        }else {
            if (bookShelfService.updateBookShelf(bookShelf)){
                resultVo.setOk(true);
                resultVo.setMess("更新成功");
                Book bookById = bookService.getBookById(bookid);
                bookById.setClick(String.valueOf(Integer.parseInt(bookById.getClick())+1));
                bookService.updateBook(bookById);
            }else {
                resultVo.setOk(false);
                resultVo.setMess("添加失败");
            }
        }
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }

    public void getChapters(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String bookid = req.getParameter("bookid");
        List<Chapter> list = chapterService.listByBook(bookid);
        resp.getWriter().write(JSONUtil.toJsonStr(list));
    }

    public void getChapter(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String cid = req.getParameter("cid");
        String bid = req.getParameter("bid");
        String num = req.getParameter("num");
        List<Object> list = new ArrayList<>();
        Book bookById = bookService.getBookById(bid);
        Chapter chapterById = chapterService.getChapterById(cid,num);
        list.add(bookById);
        list.add(chapterById);
        bookById.setClick(String.valueOf(Integer
                .parseInt(bookById.getClick())+1));
        resp.getWriter().write(JSONUtil.toJsonStr(list));
    }
    public void getBookMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String bookid = req.getParameter("bookid");
        Book bookById = bookService.getBookById(bookid);
        List<Category> categories = categoryBookService.listByBook(bookid);
        Chapter newestChapter = chapterService.getNewestChapter(bookid);
        List<Object> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (Category c:categories){
            sb.append(c.getCategory_name()+" ");
        }
        list.add(bookById);
        list.add(sb.toString());
        list.add(newestChapter);
        resp.getWriter().write(JSONUtil.toJsonStr(list));
    }

    public void listBookShelf(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String uid = ((User)req.getSession().getAttribute("user")).getId();
        String type = req.getParameter("type");
        List<BookShelfResult> bookShelfResults = new ArrayList<>();
        List<BookShelf> bookShelves = bookShelfService.listShelf(uid, type);
        for (BookShelf bookShelf:bookShelves){
            String bid = bookShelf.getBook_id();
            String cid = bookShelf.getChapter_id();
            BookShelfResult bookShelfResult = new BookShelfResult();
            bookShelfResult.setBook_id(bid);
            Book book = bookService.getBookById(bid);
            Chapter chapter = chapterService.getChapterById(cid,null);
            bookShelfResult.setBook_name(book.getBookname());
            bookShelfResult.setCover(book.getCover());
            bookShelfResult.setWriter(book.getWriter());
            bookShelfResult.setChapter_state(cid.equals("*")?"尚未阅读":"读至"+chapter.getTitle());
            bookShelfResult.setChapter_id(cid);
            Chapter newestChapter = chapterService.getNewestChapter(bid);
            String uploadTime = newestChapter.getUpload_time();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            try {
                date = fmt.parse(uploadTime);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            long sub = new Date().getTime()- date.getTime();
            bookShelfResult.setUpdate_chapter((sub/1000/60/60/2000)+"小时前更新到"+newestChapter.getTitle());
            bookShelfResults.add(bookShelfResult);
        }
        resp.getWriter().write(JSONUtil.toJsonStr(bookShelfResults));
    }
}
