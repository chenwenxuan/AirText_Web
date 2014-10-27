<%--  Created by IntelliJ IDEA.  User: xuan  Date: 14-10-22  Time: 下午6:11  To change this template use File | Settings | File Templates.--%><%@ page contentType="text/html;charset=UTF-8" language="java" %><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html><head>    <link rel="stylesheet" type="text/css" href="../../css/main.css"/>    <link rel="stylesheet" type="text/css" href="../../css/chatroom.css"/>    <script type="text/javascript">        function check_input(){            var text_value = document.getElementById("message").getAttribute("value");            if  (text_value == null || text_value.length == 0){                alert("输入不能为空");                return false;            }            else{                return true;            }        }        function control_submit(event){            if (event.ctrlKey && event.keyCode == 13){                if (check_input()){                    message_form.submit();                }            }        }    </script>    <title>暗号&nbsp;|&nbsp;${secret}</title></head><body><h1><a href="/">暗号</a>&nbsp;|&nbsp;${secret}</h1><div id="message-frame">    <c:forEach items="${messages}" var="message">        <div class="message">            <span class="message-time"><c:out value="${message.createTimeString}"/></span>            <span class="message-body" id="message-<c:out value="${message.id}"/>">                <pre><c:out value="${message.message}"/></pre>            </span>        </div>    </c:forEach></div><form id="message_form" action="/airtext/add-message" method="post" onsubmit="return check_input()">    <textarea name="message" id="message" cols="30" rows="10" onkeydown="control_submit(event)"></textarea>    <input type="submit" value="提交"/></form></body></html>