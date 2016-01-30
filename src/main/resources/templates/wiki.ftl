<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css">
    <link rel="stylesheet" href="/asset/css/sidebar.css">


    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
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
                <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">
                    Toggle Menu
                </a>
                <h1>Simple Sidebar</h1>
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
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
        integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
        crossorigin="anonymous"></script>
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
                    return "/tree";
                    //return node.id === '#' ?'ajax_demo_roots.json' : 'ajax_demo_children.json';
                },
                'data': function (node) {
                    var path;
                    if (node.id === "#")
                        path = "/"
                    else
                        path = node.id

                    return {'path': path};
                }
            }
        },
        "types": {
            "#": {
                "max_children": 1,
                "max_depth": 4,
                "valid_children": ["root"]
            },
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
            "contextmenu", "dnd", "search",
            "state", "types", "wholerow"
        ]
    }).on("changed.jstree", function (e, data) {
        console.log(data.selected);
    });
</script>

</body>
</html>