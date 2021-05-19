<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: liuxiaozhun
  Date: 2021/4/15
  Time: 10:33 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<String> list = new ArrayList<String>();
    list.add("abc1");
    list.add("abc2");
    list.add("abc3");

    for (int i = 0; i <list.size() ; i++) {
        String str = list.get(i);
%>
    <div><%=str%></div><input type="text">
<%
    }
%>
</body>
</html>
