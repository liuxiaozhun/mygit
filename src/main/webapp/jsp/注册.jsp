<%@ page import="com.bean.User" %>
<%@ page import="com.dao.UserDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.util.DBHelp" %><%--
  Created by IntelliJ IDEA.
  User: liuxiaozhun
  Date: 2021/4/14
  Time: 4:05 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    us.setUname(name);
    us.setUpass(pass);
//    boolean a = ud.add(us);
//    if (a){
//        response.sendRedirect("../index.html");
//    }else {
//        response.sendRedirect("../html/注册.html");
//    }

%>
</body>
</html>
