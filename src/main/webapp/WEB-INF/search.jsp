<%@page import="com.hrm.Models.RegisterModel"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Details</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="container">
    <h1>User Details</h1>
    <%
        RegisterModel model = (RegisterModel) request.getAttribute("model");
        if (model != null) {
    %>
    <table>
        <tr>
            <th>ID</th>
            <td><%= model.getUserId() %></td>
        </tr>
        <tr>
            <th>Username</th>
            <td><%= model.getUserName() %></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><%= model.getEmail() %></td>
        </tr>
        <tr>
            <th>Address</th>
            <td><%= model.getAddress() %></td>
        </tr>
        <tr>
            <th>Mobile No</th>
            <td><%= model.getMobileNo() %></td>
        </tr>
        <tr>
            <th>Register Date</th>
            <td><%= model.getRegisterDate() %></td>
        </tr>
        <tr>
            <th>Parent Mail</th>
            <td><%= model.getPmailId() %></td>
        </tr>
    </table>
    <div class="button-container">
        <a href="updateUser?userId=<%= model.getUserId() %>" class="update-button">Update</a>
        <button class="back-button" onclick="window.history.back();">Back</button>
    </div>
    <%
        } else {
    %>
    <p>No user found with the given search term.</p>
    <%
        }
    %>
</div>
</body>
</html>
