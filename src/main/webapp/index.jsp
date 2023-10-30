<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Main Page</title>
    <%@ include file="/WEB-INF/header.jsp" %>
</head>
<body style="background-color: gainsboro">

<h1>Welcome to the main page</h1>
<c:redirect url = "mainWindow?action=main"/>
<%@ include file="/WEB-INF/footer.jsp" %>
</body>
</html>