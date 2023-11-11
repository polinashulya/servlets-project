<%--
  Created by IntelliJ IDEA.
  User: polinashulya
  Date: 10.11.2023
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .error-container {
            text-align: center;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 40px;
        }

        h1 {
            color: #333;
        }

        p {
            color: #777;
        }
    </style>
</head>
<body>
<div class="error-container">
        <img src="pictures/error_page/404.png" alt=""/>
        <p>
            <a href="mainWindow?action=main">
                Go back to main page
            </a>
        </p>
</div>
<%@ include file="/WEB-INF/footer.jsp" %>
</body>
</html>
