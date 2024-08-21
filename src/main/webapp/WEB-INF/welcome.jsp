<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #e9ecef;
    margin: 0;
    padding: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

form {
    background: #ffffff; /* Light background color for the form */
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
    width: 50%;
}

h2 {
    color: #343a40;
    text-align: center;
    margin-bottom: 25px;
    font-size: 24px;
    font-weight: bold;
}

table {
    width: 100%;
    border-spacing: 0 15px; /* Add space between rows */
}

td {
    padding: 10px;
    vertical-align: middle;
}

input[type="text"], input[type="textarea"] {
    width: 100%;
    padding: 10px;
    margin: 5px 0;
    box-sizing: border-box;
    border: 1px solid #ced4da;
    border-radius: 4px;
    font-size: 16px;
}

input[type="checkbox"] {
    margin-right: 10px;
    transform: scale(1.2);
}

input[type="submit"], .back-button, .login-button {
    background-color: #007bff;
    color: white;
    padding: 12px 25px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s ease;
}

input[type="submit"]:hover, .back-button:hover, .login-button:hover {
    background-color: #0056b3;
}

.button-container {
    text-align: center;
    margin-top: 30px;
}

.back-button {
    background-color: #6c757d;
    margin-right: 15px;
}

.login-button {
    background-color: #28a745;
    margin-left: 15px;
}

@media (max-width: 768px) {
    form {
        width: 80%;
    }

    td {
        display: block;
        width: 100%;
    }

    td:nth-child(odd) {
        margin-bottom: 10px;
    }

    input[type="submit"], .back-button, .login-button {
        width: 100%;
        margin-bottom: 10px;
    }

    .button-container {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
}
</style>
</head>
<body>
<form action="register" method="post">
<h2><b>User Register Form</b></h2>
<table>
<tr>
<td>User Name</td>
<td><input type="text" name="userName" placeholder="Enter your name"></td>
<td>Email</td>
<td><input type="text" name="email" placeholder="Enter your email"></td>
</tr>
<tr>
<td>Address</td>
<td><input type="textarea" name="address" placeholder="Enter your address"></td>
<td>Mobile No.</td>
<td><input type="text" name="mobileNo" placeholder="Enter your mobile number"></td>
</tr>
<tr>
<td>Topic</td>
<td><input type="checkbox" name="topic1">Java</td>
<td><input type="checkbox" name="topic2">Python</td>
<td><input type="checkbox" name="topic3">Angular</td>
</tr>
<tr>
<td>Register Date</td>
<td><input type="text" name="registerDate" placeholder="Enter the register date"></td>
<td>Parent Mail</td>
<td><input type="text" name="pmailId" placeholder="Enter parent's email"></td>
</tr>
<tr>
<td>Password</td>
<td><input type="text" name="password" placeholder="Enter your password"></td>
</tr>
</table>
<div class="button-container">
    <input type="submit" value="Register User">
    <button type="button" class="login-button" onclick="window.location.href='login.jsp';">Login</button>
</div>
</form>
</body>
</html>
