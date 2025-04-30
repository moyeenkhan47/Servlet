<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Session Expired</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 50px;
            background-color: #f4f4f4;
        }
        .container {
            display: inline-block;
            padding: 20px;
            border: 1px solid #ccc;
            background-color: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
        }
        p {
            margin: 20px 0;
            color: #666;
        }
        a {
            color: #007BFF;
            text-decoration: none;
            margin: 5px 10px;
            padding: 10px 20px;
            border: 1px solid #007BFF;
            border-radius: 5px;
            display: inline-block;
        }
        a:hover {
            background-color: #007BFF;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Session Expired</h1>
        <p>Your session has expired due to inactivity or because you logged out.</p>
        <p>Please log in again to continue.</p>
        <a href="login.jsp">Log In</a>
        <a href="index.jsp">Go to Home</a>
    </div>
</body>
</html>
