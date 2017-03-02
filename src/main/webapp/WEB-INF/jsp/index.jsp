<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello React</title>

    <!-- Dependencies -->
    <script type="text/javascript" src="js/bower_components/react/react.min.js"></script>
    <script type="text/javascript" src="js/bower_components/showdown/compressed/Showdown.min.js"></script>
    <script type="text/javascript" src="js/bower_components/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <link href="js/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="css/comments.css" rel="stylesheet">
</head>
<body>
<div id="content" class="container">${content}</div>
<script type="text/javascript" src="js/gen/commentBox.js"></script>
<script type="text/javascript">
    $(function () {
        renderClient(${data});
    });
</script>
</body>
</html>