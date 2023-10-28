<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Adding book</title>
</head>
<body style="background-color: gainsboro">
<%@ include file="/WEB-INF/header.jsp" %>
<h1>Add new user</h1>
<form method="POST">
    Login :
    <input type="text" name="login" required="required" value="<c:out value="${user.login}" />"/>
    <br/>

    Password :
    <input type="text" name="password" required="required" value="<c:out value="${user.password}" />"/>
    <br/>

    First name :
    <input type="text" name="firstName" required="required" value="<c:out value="${user.firstName}" />"/>
    <br/>

    Second name :
    <input type="text" name="secondName" required="required" value="<c:out value="${user.secondName}" />"/>
    <br/>

    Birth date :
    <input type="text" name="birthDate" required="required" value="<c:out value="${user.birthDate}" />"/>
    <br/>

    <input type="submit" value="Submit"/>
</form>
<%@ include file="/WEB-INF/footer.jsp" %>
</body>
</html>