package com.server;

import com.bean.User;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MySercvlet1 extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        User us = new User();
        UserDao ud = new UserDao();
        HttpServletRequest rea = (HttpServletRequest)req;
        HttpServletResponse reb = (HttpServletResponse)res;
        String name = rea.getParameter("na");
        String pass = rea.getParameter("ps");
        us.setUname(name);
        us.setUpass(pass);
//        boolean a = ud.add(us);
//        if (a){
//            reb.sendRedirect("index.html");
//        }else {
//            reb.sendRedirect("https://www.pianku.li");
//        }
    }
}
