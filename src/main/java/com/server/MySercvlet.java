package com.server;

import com.bean.User;
import com.dao.UserDao;
import com.util.DBHelp;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 1继承HttpServlet类
 */
public class MySercvlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        //      User us = new User();
        UserDao ud = new UserDao();
        HttpServletRequest rea = (HttpServletRequest)req;
        HttpServletResponse reb = (HttpServletResponse)res;
        String name = rea.getParameter("na");
        System.out.println(name);
        String pass = rea.getParameter("ps");
//       us.setName(name);
//       us.setPass(pass);
        String sql = "SELECT * FROM `IUser` WHERE uname=? AND upass=?";
        List list = DBHelp.comQuery(sql,User.class,name,pass);
        String sql1 = "SELECT * FROM IUser";
        List list1 = DBHelp.comQuery(sql1,User.class);
//      boolean a = ud.add(us);
        //System.out.println(list.size());
        if (list.size() != 0){
            HttpSession session = rea.getSession();
            session.setAttribute("na",list);
            session.setAttribute("nb",list1);
            reb.sendRedirect("jsp/logindown.jsp");
        }else {
            reb.sendRedirect("jsp/loginfalse.jsp");
        }
    }

    @Override
    public void init() throws ServletException {
        System.out.println("MySercvlet init");
    }

    @Override
    public void destroy() {
        System.out.println("MySercvlet destroy");
    }
}
