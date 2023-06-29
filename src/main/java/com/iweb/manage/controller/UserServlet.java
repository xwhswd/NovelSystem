package com.iweb.manage.controller;

import cn.hutool.json.JSONUtil;
import com.iweb.manage.pojo.ResultVo;
import com.iweb.manage.pojo.User;
import com.iweb.manage.pojo.UserMessage;
import com.iweb.manage.service.UserService;
import com.iweb.manage.service.impl.UserServiceImpl;
import com.iweb.manage.utils.MD5Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Map;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet{

    public void login(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User login = userService.login(user);
        ResultVo resultVo = new ResultVo();
        if (login!=null){
            resultVo.setOk(true);
            req.getSession().setAttribute("user",login);
            resultVo.setMess("登陆成功");
        }else {
            resultVo.setOk(false);
            resultVo.setMess("登录失败");
        }
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }

    public void logOut(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        req.getSession().removeAttribute("user");
        resp.sendRedirect("http://localhost:8080/read/index.html");
    }

    public void verifyUsername(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        String username = req.getParameter("username");
        ResultVo resultVo = new ResultVo();
        if (userService.verifyUsername(username)){
            resultVo.setOk(true);
            resultVo.setMess("用户名重复");
        }
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }
    public void register(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean isRegistered = userService.register(user);
        ResultVo resultVo = new ResultVo();
        if (isRegistered){
            resultVo.setOk(true);
            resultVo.setMess("注册成功");
        }
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }
    public void checkLogin(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        ResultVo<User> resultVo = new ResultVo();
        User user = (User) req.getSession().getAttribute("user");
        if (req.getSession().getAttribute("user")!=null){
            resultVo.setOk(true);
            resultVo.setMess("已经登录");
            resultVo.setT(user);
        }
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }

    public void getUserMessage(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        User user = (User) req.getSession().getAttribute("user");
        int count=0;
        if (user.getPhone()!=null){
            count++;
        }
        if (user.getEmail()!=null){
            count++;
        }
        UserMessage userMessage = new UserMessage();
        userMessage.setUid(user.getId());
        userMessage.setUsername(user.getUsername());
        userMessage.setPhone(user.getPhone());
        userMessage.setEmail(user.getEmail());
        userMessage.setBalance(user.getBalance());
        userMessage.setCollect(String.valueOf(bookShelfService.count(user.getId())));
        userMessage.setRegister_time(user.getCreate_time());
        userMessage.setPercent(String.valueOf((2+count)*100/4));
        resp.getWriter().write(JSONUtil.toJsonStr(userMessage));
    }

    public void updateUser(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        User user = (User)req.getSession().getAttribute("user");
        String pwd = req.getParameter("originPwd");
        String repwd = req.getParameter("newPwd");
        String email = req.getParameter("email");
        String remail = req.getParameter("remail");
        String phone = req.getParameter("phone");
        String rephone = req.getParameter("rephone");
        ResultVo resultVo = new ResultVo();
        if (pwd!=null&&repwd!=null){
            if (MD5Util.getMD5(pwd).equals(user.getPassword())){
                user.setPassword(MD5Util.getMD5(repwd));
                boolean flag = userService.updateUser(user);
                if (flag) {
                    resultVo.setOk(true);
                    resultVo.setMess("更新密码成功");
                }
            }
        }else if (email!=null&&remail!=null){
            if (email.equals(user.getEmail())){
                user.setEmail(remail);
                boolean flag= userService.updateUser(user);
                if (flag) {
                    resultVo.setOk(true);
                    resultVo.setMess("更新邮箱成功");
                }
            }
        }else if (phone!=null&&rephone!=null){
            if (phone.equals(user.getPhone())){
                user.setPhone(rephone);
                boolean flag = userService.updateUser(user);
                if (flag) {
                    resultVo.setOk(true);
                    resultVo.setMess("更新手机号码成功");
                }
            }
        }
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }

    public void uploadHead(){

    }
}
