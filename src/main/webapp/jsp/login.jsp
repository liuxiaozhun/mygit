<%@ page import="com.bean.User" %>
<%@ page import="com.dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: liuxiaozhun
  Date: 2021/4/14
  Time: 4:48 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    User us = new User();
    UserDao ud = new UserDao();
    String name = request.getParameter("na");
    String pass = request.getParameter("ps");
    us.setuName(name);
    us.setPass(pass);
    boolean a = ud.select(us);
    if (a){
        response.sendRedirect("logindown.jsp");
    }else {
        response.sendRedirect("loginfalse.jsp");
    }
%>
</body>
</html>
