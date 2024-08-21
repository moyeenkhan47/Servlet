<%@ page import="com.hrm.Models.RegisterModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update User Page</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f0f2f5;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    text-align: center;
}

form {
    background: #ffffff;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
    width: 80%;
    max-width: 700px;
    box-sizing: border-box;
}

h2 {
    color: #343a40;
    font-size: 24px;
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-spacing: 0 15px;
}

td {
    padding: 10px;
    vertical-align: middle;
    text-align: left;
    font-size: 16px;
}

input[type="text"], textarea {
    width: 100%;
    padding: 10px;
    margin: 5px 0;
    box-sizing: border-box;
    border: 1px solid #ced4da;
    border-radius: 4px;
    font-size: 16px;
}

textarea {
    height: 100px;
    resize: vertical;
}

input[type="submit"], .back-button {
    background-color: #007bff;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    margin-top: 20px;
    transition: background-color 0.3s ease;
    width: 48%;
}

input[type="submit"]:hover, .back-button:hover {
    background-color: #0056b3;
}

.back-button {
    background-color: #6c757d;
    margin-right: 10px;
}

.button-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

@media (max-width: 768px) {
    form {
        width: 90%;
        padding: 20px;
    }

    input[type="submit"], .back-button {
        width: 100%;
        margin: 10px 0;
    }

    .button-container {
        flex-direction: column;
    }
}
</style>
</head>
<body>
<% 
    RegisterModel model = (RegisterModel) request.getAttribute("model");
    if (model != null) {
%>
<form action="updateUser" method="post">
    <input type="hidden" name="userId" value="<%= model.getUserId() %>">
    <h2><b>Update User</b></h2>
    <table>
        <tr>
            <td>User name</td>
            <td><input type="text" name="userName" value="<%= model.getUserName() %>"></td>
            <td>Email</td>
            <td><input type="text" name="email" value="<%= model.getEmail() %>"></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><textarea name="address"><%= model.getAddress() %></textarea></td>
            <td>Mobile No.</td>
            <td><input type="text" name="mobileNo" value="<%= model.getMobileNo() %>"></td>
        </tr>
        <tr>
            <td>Register Date</td>
            <td><input type="text" name="registerDate" value="<%= model.getRegisterDate() %>"></td>
            <td>Parent Mail</td>
            <td><input type="text" name="pmailId" value="<%= model.getPmailId() %>"></td>
        </tr>
    </table>
    <div class="button-container">
        <button type="button" class="back-button" onclick="window.history.back();">Back</button>
        <input type="submit" value="Update User">
    </div>
</form>
<% 
    } else {
%>
<p>User information not found.</p>
<% 
    }
%>
</body>
</html>
