<%@ page contentType="text/html;charset=UTF-8" language="java" %><html><head>    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />    <link rel="stylesheet" type="text/css" href="css/main.css"/>    <link rel="stylesheet" type="text/css" href="css/index.css">    <style type="text/css">        input {            display: block        }    </style>    <title>暗号</title></head>```````<body onload="document.getElementById('secret-input').focus()"><h1><a href="/">暗号</a></h1></body><form id="secret-form" action="/airtext/start-chat" method="post">    <input type="text" id="secret-input" name="secret" placeholder="请输入暗号" autocomplete="off"/>    <input type="submit" value="进入"/></form></html>