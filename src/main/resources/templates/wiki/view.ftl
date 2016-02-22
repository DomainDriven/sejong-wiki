<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>위키 - ${title}</title>

    <!-- Bootstrap -->
    <link rel="stylesheet"
          href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="//cdnjs.cloudflare.com/ajax/libs/jqtree/1.3.0/jqtree.min.css">

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
                <div id="jqtree" data-url="/wiki/tree">
                </div>
            </div>
        </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="col-lg-12">
                <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle
                    Menu</a>
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
<script src="//cdnjs.cloudflare.com/ajax/libs/jqtree/1.3.0/tree.jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    // http://mbraak.github.io/jqTree
    $('#jqtree').tree({
        saveState: true,
        selectable: false
        /*options*/
    }).bind(
            'tree.click',
            function(event) {
                // The clicked node is 'event.node'
                var node = event.node;
                console.log("selected node", node);

                // leaf노드를 선택했을 경우에만 동작.
                if(node.load_on_demand=== false){
                    location.href = "/w/" + node.id;
                }
            }
    );
</script>

</body>
</html>