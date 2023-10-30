<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style type="text/css">
        <%@ include file="/WEB-INF/css/table.css" %>
    </style>
</head>
<body>
<%@ include file="/WEB-INF/header.jsp" %>
<div id="main">
    <table class="timecard">
        <caption>Users</caption>
        <thead>
        <tr>
            <th id="id">User`s id</th>
            <th id="login">Login</th>
            <th id="firstName">First name</th>
            <th id="secondName">Second name</th>
            <th id="birthDate">Birth date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.secondName}</td>
                <td>${user.birthDate}</td>
                <td>
                    <form action="mainServlet?action=delete_user" method="post">
                        <input type="hidden" name="action" value="delete_user"/>
                        <input type="hidden" name="id" value="${user.id}"/>
                        <button type="submit" class="delete-button">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="/WEB-INF/footer.jsp" %>
</body>
</html>