<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>위키 - ${title}</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css">

    <link rel="stylesheet" href="/asset/css/wiki.css">

    <!--[if lt IE 9]>
    <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div id="wrapper">
    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <div id="sidebar">
            <div class="row sidebar-brand">
                <h1>세종위키</h1>
            </div>
            <div class="row" id="docList">
                <!--TODO 우선은 docTree로..-->
                <div id="jstree">
                </div>
            </div>
        </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="col-lg-12">
                <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a>
                <h1>${title}</h1>
                <div class="content">
                    ${html}
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->
</div>
<!-- /#wrapper -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    $('#jstree').jstree({
        "core": {
            "animation": 0,
            "check_callback": true,
            'data': {
                'url': function (node) {
                    return "/wiki/tree";
                    //return node.id === '#' ?'ajax_demo_roots.json' : 'ajax_demo_children.json';
                },
                'data': function (node) {
                    var path;
                    if (node.id === "#")
                        path = "/";
                    else
                        path = node.id;

                    return {'path': path};
                }
            }
        },
        "types": {
            "root": {
                "icon": "/static/3.2.1/assets/images/tree_icon.png"
            },
            "FILE": {
                "icon": "glyphicon glyphicon-file"
            },
            "DIRECTORY":{
                "icon" : "glyphicon glyphicon-folder-close"
            }
        },
        "plugins": [
            //"dnd",//"search", //"state",
            "types", "wholerow"
        ]
    }).on("select_node.jstree", function (e, data) {
        if(data.node.type === "FILE")
            location.href = "/w" + data.node.id;
    });
</script>

</body>
</html>