<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body style="background-color: gainsboro">

<div id="main">
    <table class="timecard">
        <caption>Users</caption>
        <thead>
        <tr>
            <th id="id">Book`s id</th>
            <th id="title">Title</th>
            <th id="numberOfPage">Number of page</th>
            <th id="releaseYear">Release Year</th>
        </tr>
        </thead>
        <tbody>
        <%--        <c:forEach items="${books}" var="book">--%>
        <%--            <tr>--%>
        <%--                <td><c:out value="${book.id}"/></td>--%>
        <%--                <td><c:out value="${book.title}"/></td>--%>
        <%--                <td><c:out value="${book.numberOfPage}"/></td>--%>
        <%--                <td><c:out value="${book.releaseYear}"/></td>--%>
        <%--            </tr>--%>
        <%--        </c:forEach>--%>
        </tbody>
    </table>
</div>

</body>
</html>