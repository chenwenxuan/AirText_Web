ZeroClipboard.setMoviePath("../../swf/ZeroClipboard.swf");
clipArray = new Array();

$(window).resize(function () {
    for (i in clipArray) {
        clip = clipArray[i];
        clip.reposition();
    }
})

$(document).ready(function () {
    $('pre').each(
        function (index, item) {
            item.innerHTML = enable_java_code(item.innerHTML);
            item.innerHTML = enable_url(item.innerHTML);
        });

    $('.copy-button').each(
        function (index, item) {
            var _id = item.id.replace("copy_button_", "");
            var span_id = "message_" + _id;
            var text_value = document.getElementById(span_id).innerText;

            var clip = new ZeroClipboard.Client();
            clip.setHandCursor(true);
            clip.setText(text_value);
            clip.glue(item.id);
            clip.setCSSEffects(true);

            clip.addEventListener("complete", function (client) {
                show_hint("已复制内容至剪贴板", 1);
            })

            clipArray.push(clip);
        }
    )

    $('.delete-button').click(function () {
        var confirm = window.confirm("确认删除此条记录？");
        if (!confirm) {
            return;
        }
        var num_id = $(event.target).attr("id").replace("delete_button_","");

        var millis = getCookie("page_time");
        if(millis == null){
            millis = "0";
        }
        var request_url = "/airtext/delete/?message_id="+num_id+"&millis="+millis;
        $.ajax({
            url:request_url,
            success:function(data){
                if(data.errorMsg == null || data.errorMsg.length == 0){
                    console.log(JSON.stringify(data));
                    show_hint("删除成功",1);
                    $('#message_frame_'+num_id).remove();
                }
            },
            dataType:null
        })
    })
});

function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

    if (arr = document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

var hint_timeout;

function show_hint(hint, seconds) {
    clearTimeout(hint_timeout);
    $('.hint-div').remove();
    hint_ele = $("<div class='hint-div'><span class='hint-span'>" + hint + "</span></div>");
    $("body").append(hint_ele);
    hint_timeout = setTimeout("$('.hint-div').remove()", seconds * 1000);
}

function enable_url(text_value) {
    var exp = /(\b(https?|ftp|file):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/ig;
    text_value = text_value.replace(exp, "<a href='$1'>$1</a>");
    return text_value;
}

function enable_java_code(text_value) {
    var exp_keywords = /((break|case|catch|char|continue|double|do|else|final|finally|float|for|if|implements|import|instanceof|int|interface|long|private|protected|public|return|short|static|this|throw|throws|transient|try|void|volatile|while)(\[\])*\s)/ig;
    text_value = text_value.replace(exp_keywords, "<span class='code-keywords'>$1</span>");
    var exp_brackets = /([\(\)\[\]\{\]\}])/ig;
    text_value = text_value.replace(exp_brackets, "<span class='code-brackets'>$1</span>");
    var exp_strings = /(\".*?[^\\]\")/ig;
    text_value = text_value.replace(exp_strings, "<span class='code-strings'>$1</span>");
    return text_value;
}

function is_text_code(text_value) {
    var exp_keywords = /((break|case|catch|char|continue|double|do|else|final|finally|float|for|if|implements|import|instanceof|int|interface|long|private|protected|public|return|short|static|this|throw|throws|transient|try|void|volatile|while)(\[\])*\s)/ig;
    text_value.match(exp_keywords);

}

function check_input() {
    var text_value = document.getElementById("message").value;
    var emptyPattern = new RegExp("^\\s*$");
    if (emptyPattern.test(text_value)) {
        show_hint("输入不能为空", 1);
        return false;
    }
    else {
        return true;
    }
}

function control_submit(event) {
    if (event.ctrlKey && event.keyCode == 13) {
        if (check_input()) {
            message_form.submit();
        }
    }
}
