<%--
  Created by IntelliJ IDEA.
  User: xuan
  Date: 14-10-23
  Time: 下午10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/main.css" />
    <link rel="stylesheet" type="text/css" href="../../css/startchat.css"/>
    <style>
        input[type=text]{visibility: hidden;display: block}
    </style>
    <title></title>
</head>
<body>
    <h1><a href="/">暗号</a></h1>
    <form action="/airtext/start-chat" method="post">
        <input type="text" name="secret" value="${secret}"/>
        <input type="submit" value="创建暗号 ${secret}"/>
    </form>
</body>
</html>
