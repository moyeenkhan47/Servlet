<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HRMS Dashboard</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f8f9fa;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: flex-start;
    height: 100vh;
}

h1 {
    color: #343a40;
    margin-bottom: 20px;
    text-align: center;
}

.container {
    width: 90%;
    max-width: 800px;
    margin: 20px auto;
    background: #ffffff;
    padding: 30px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    text-align: center;
}

a {
    text-decoration: none;
    color: #007bff;
    margin: 0 10px;
    font-size: 18px;
    transition: color 0.3s ease;
}

a:hover {
    color: #0056b3;
}

.container a {
    display: inline-block;
    padding: 10px 20px;
    background: #007bff;
    color: #fff;
    border-radius: 5px;
    margin-top: 10px;
}

.container a:first-child {
    margin-right: 20px;
}
</style>
</head>
<body>
<div class="container">
    <h1>HRMS Project Dashboard</h1>
    <a href="register.jsp">Register</a>
    <a href="register">Find All User</a>
</div>
</body>
</html>
