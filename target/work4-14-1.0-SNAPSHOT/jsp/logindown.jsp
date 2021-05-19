<%--
  Created by IntelliJ IDEA.
  User: liuxiaozhun
  Date: 2021/4/14
  Time: 4:53 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1 style="color: aqua">登录成功</h1>
欢迎<c:out value="${na.get(0).uname}" />访问
<%--<c:forEach var="User" items="${nb}" />--%>
<table style="background: cadetblue" border="1">
<c:forEach var="list" items="${nb}" >
    <tr>
        <td>${list.id}</td>
        <td>${list.uname}</td>
        <td>${list.upass}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
