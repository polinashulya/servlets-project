<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <style type="text/css">
        <%@ include file="/WEB-INF/css/header.css" %>
    </style>
    <title></title>
</head>
<body>
<header class="header">
    <div class="button-container">
        <a class="button" href="mainServlet">Main page</a>
        <a class="button" href="userServlet?action=users">Show all users</a>
        <a class="button" href="userServlet?action=add_user">Add new user</a>
    </div>
</header>
</body>
</html>
