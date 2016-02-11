<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>타이틀은 없어!</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.11.0/codemirror.min.css">
    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

        /* CSS는 정리필요!! */
        .preview {
            position: absolute;
            border-left: black 1px solid;
            overflow-y: scroll;
            padding-left : 10px;
            top: 50px;
            bottom: 50px;
            left: 50%;
            right: 0;
        }

        .editor {
            position: absolute;
            border-left: black 1px solid;
            overflow-y: scroll;
            top: 50px;
            bottom: 50px;
            left: 0;
            right: 50%;
        }

        .path-wrapper > h1 {
            margin: 0;
        }

        .editor-button {
            position: absolute;
            bottom: 0;
            right : 0;
            margin : 10px;
        }

        .form-group {
            margin-right: 20px;
        }

        .form-group {
            margin-right: 20px;
        }

        #documentBody {
            display: none;
        }
    </style>

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="wrapper">

    <div class="path-wrapper">
        <h1 class="path">
            <small>path :</small>
            ${path}
        </h1>
    </div>

    <div class="editor-wrapper">
        <div class="editor">
            <textarea id="documentBody">${body}</textarea>
        </div>
        <div class="preview"></div>
    </div>

    <div class="editor-button">
        <form class="form-inline" method="post" action="/w/${path}">
            <input type="hidden" name="body" id="inputBody">
            <div class="form-group">
                <input type="text" class="form-control" name="comment" id="versionComment" placeholder="What did you change?">
            </div>
            <button type="submit" class="btn btn-default">Save</button>
            <button class="btn btn-default">Close</button>
        </form>
    </div>

</div>

<#--<!-- jQuery (necessary for Bootstrap's JavaScript plugins) &ndash;&gt;-->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
        integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
        crossorigin="anonymous"></script>

<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.11.0/codemirror.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.11.0/mode/javascript/javascript.min.js"></script>

<script>
    var textArea = document.getElementById("documentBody");
    var cm = CodeMirror(function (elt) {
        textArea.parentNode.replaceChild(elt, textArea);
    }, {
        value: textArea.value,
        lineNumbers: true,
        matchBrackets: true
    });
    cm.setSize("100%", "100%");

    var parseBody = function (body, docType) {
        $.get("/wiki/parsing", {"body": body, docType: docType}).success(function (data) {
            $(".preview").html(data);
        });
    };

    var tmpBody = "";

    // 2초마다 검사하여, 변경사항이 있을 경우 파싱.
    setInterval(function () {
        var body = cm.getValue();
        if (tmpBody !== body) {
            tmpBody = body;
            // TODO 문서타입 가져오기.
            parseBody(body, "md");
        }
    }, 1000);

    //저장 버튼을 누를 경우 서버로 정보를 전달한다.
    $(".editor-button").on("submit", function(e){
        if(confirm("저장하시겠습니까?")){
            var body = cm.getValue();
            $("#inputBody").value(body);
        } else {
            return false;
        }
    });
</script>

</body>
</html>