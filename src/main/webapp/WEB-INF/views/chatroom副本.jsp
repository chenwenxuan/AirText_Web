<%--  Created by IntelliJ IDEA.  User: xuan  Date: 14-10-22  Time: 下午6:11  To change this template use File | Settings | File Templates.--%><%@ page contentType="text/html;charset=UTF-8" language="java" %><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html><head>    <link rel="stylesheet" type="text/css" href="../../css/main.css"/>    <link rel="stylesheet" type="text/css" href="../../css/chatroom.css"/>    <script type="text/javascript" src="../../js/jquery.min.js"></script>    <script type="text/javascript" src="../../js/ZeroClipboard.js"></script>    <script type="text/javascript">        ZeroClipboard.setMoviePath("../../swf/ZeroClipboard.swf");        clipArray = new Array();        $(window).resize(function(){            for (i in clipArray){                clip = clipArray[i];                clip.reposition();            }        })        $(document).ready(function () {            $('pre').each(                    function (index, item) {                        item.innerHTML = enable_java_code(item.innerHTML);                        item.innerHTML = enable_url(item.innerHTML);                    });            $('.copy-button').each(                    function (index, item){                        var _id = item.id.replace("copy_button_","");                        var span_id = "message_"+_id;                        var text_value = document.getElementById(span_id).innerText;                        var clip = new ZeroClipboard.Client();                        clip.setHandCursor(true);                        clip.setText(text_value);                        clip.glue(item.id);                        clip.setCSSEffects(true);                        clip.addEventListener("complete",function(client){                            alert("复制成功，请用Ctrl+V粘贴。");                        })                        clipArray.push(clip);                    }            )        });////        String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {//            if (!RegExp.prototype.isPrototypeOf(reallyDo)) {//                return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")), replaceWith);//            } else {//                return this.replace(reallyDo, replaceWith);//            }//        }        function enable_url(text_value) {            var exp = /(\b(https?|ftp|file):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/ig;            text_value = text_value.replace(exp, "<a href='$1'>$1</a>");//            var exp_2 = /([^(https?|ftp|file):\/\/]([-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|]))/ig;//            text_value = text_value.replace(exp, "<a href='http://$1'>$1</a>")            return text_value;        }        function enable_java_code(text_value){            var exp_keywords = /((break|case|catch|char|continue|double|do|else|final|finally|float|for|if|implements|import|instanceof|int|interface|long|private|protected|public|return|short|static|this|throw|throws|transient|try|void|volatile|while)(\[\])*\s)/ig;            var exp_brackets = /([\(\)\[\]\{\]\}])/ig;            var exp_strings = /(\".*?[^\\]\")/ig;            return text_value.replace(exp_keywords,"<span class='code-keywords'>$1</span>").replace(exp_brackets,"<span class='code-brackets'>$1</span>").replace(exp_strings,"<span class='code-strings'>$1</span>");        }        function check_input() {            var text_value = document.getElementById("message").value;            var emptyPattern = new RegExp("^\\s*$");            if (emptyPattern.test(text_value)) {                alert("输入不能为空");                return false;            }            else {                return true;            }        }        function control_submit(event) {            if (event.ctrlKey && event.keyCode == 13) {                if (check_input()) {                    message_form.submit();                }            }        }    </script>    <title>暗号&nbsp;|&nbsp;${secret}</title></head><body><h1><a href="/">暗号</a>&nbsp;|&nbsp;${secret}</h1><div id="message-frame">    <c:forEach items="${messages}" var="message">        <div class="message">            <span class="message-time"><c:out value="${message.createTimeString}"/></span>            <span class="message-body" id="message_<c:out value="${message.id}"/>">                <pre><c:out value="${message.message}"/></pre>            </span>            <button class="copy-button" id="copy_button_<c:out value="${message.id}"/>">复制</button>        </div>    </c:forEach></div><form id="message_form" action="/airtext/add-message" method="post" onsubmit="return check_input()">    <textarea name="message" id="message" cols="30" rows="10" onkeydown="control_submit(event)"></textarea>    <input type="submit" value="提交"/></form></body></html>