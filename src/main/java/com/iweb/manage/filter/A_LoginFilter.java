package com.iweb.manage.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author xwh
 * @version 1.0
 * 2023/6/27
 */
@WebFilter("/*")
public class A_LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        if (uri.endsWith(".png")||uri.endsWith(".jpg")||uri.endsWith(".jpeg")||uri.endsWith(".css")||uri.endsWith(".js")
            ||uri.endsWith(".svg")||uri.endsWith(".json")||uri.endsWith(".tff")||uri.endsWith(".woff")||uri.endsWith(".woff2")
            ||uri.endsWith("index.html")||uri.endsWith(".yml")||uri.endsWith("404.html")||uri.endsWith("about.html")
            ||uri.contains("user")){
            chain.doFilter(req,resp);
            return;
        }
        HttpSession session = req.getSession();
        if (session.getAttribute("user")!=null){
            chain.doFilter(req,resp);
            return;
        }else{
            resp.sendRedirect("index.html");
        }
    }

    @Override
    public void destroy() {

    }
}
