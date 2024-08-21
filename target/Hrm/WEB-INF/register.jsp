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
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background: #ffffff; /* Light background color for the form */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 60%;
        }

        h2 {
            color: #343a40;
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        td {
            padding: 10px;
            vertical-align: middle;
        }

        input[type="text"], textarea {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="checkbox"] {
            margin-right: 10px;
        }

        input[type="submit"], .login-button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-right: 10px; /* Space between buttons */
        }

        input[type="submit"]:hover, .login-button:hover {
            background-color: #0056b3;
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }

        .login-button {
            background-color: #6c757d; /* Different color for the login button */
        }

        .login-button:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
<form action="register" method="post">
    <h2><b>User Register Form</b></h2>
    <table>
        <tr>
            <td>User Name</td>
            <td><input type="text" name="userName" required></td>
            <td>Email</td>
            <td><input type="text" name="email" required></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><textarea name="address" rows="3" required></textarea></td>
            <td>Mobile No.</td>
            <td><input type="text" name="mobileNo" required></td>
        </tr>
        <tr>
            <td>Topic</td>
            <td><input type="checkbox" name="topic1">Java</td>
            <td><input type="checkbox" name="topic2">Python</td>
            <td><input type="checkbox" name="topic3">Angular</td>
        </tr>
        <tr>
            <td>Register Date</td>
            <td><input type="text" name="registerDate" required></td>
            <td>Parent Mail</td>
            <td><input type="text" name="pmailId" required></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" required></td>
        </tr>
    </table>
    <div class="button-container">
        <button type="button" class="login-button" onclick="window.location.href='login.jsp';">Login</button>
        <input type="submit" value="Register User">
    </div>
</form>
</body>
</html>
