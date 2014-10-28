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
                alert("复制成功，请用Ctrl+V粘贴。");
            })

            clipArray.push(clip);
        }
    )
});
//
//        String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {
//            if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
//                return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")), replaceWith);
//            } else {
//                return this.replace(reallyDo, replaceWith);
//            }
//        }

function enable_url(text_value) {
    var exp = /(\b(https?|ftp|file):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/ig;
    text_value = text_value.replace(exp, "<a href='$1'>$1</a>");

//            var exp_2 = /([^(https?|ftp|file):\/\/]([-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|]))/ig;
//            text_value = text_value.replace(exp, "<a href='http://$1'>$1</a>")
    return text_value;
}

function enable_java_code(text_value) {
    var exp_keywords = /((break|case|catch|char|continue|double|do|else|final|finally|float|for|if|implements|import|instanceof|int|interface|long|private|protected|public|return|short|static|this|throw|throws|transient|try|void|volatile|while)(\[\])*\s)/ig;
    var exp_brackets = /([\(\)\[\]\{\]\}])/ig;
    var exp_strings = /(\".*?[^\\]\")/ig;
    return text_value.replace(exp_keywords, "<span class='code-keywords'>$1</span>").replace(exp_brackets, "<span class='code-brackets'>$1</span>").replace(exp_strings, "<span class='code-strings'>$1</span>");
}

function check_input() {
    var text_value = document.getElementById("message").value;
    var emptyPattern = new RegExp("^\\s*$");
    if (emptyPattern.test(text_value)) {
        alert("输入不能为空");
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
