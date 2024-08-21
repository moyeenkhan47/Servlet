<%@page import="com.hrm.Models.RegisterModel"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Details</title>
<link rel="stylesheet" type="text/css" href="styles.css">
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f9fa;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
    }

    .container {
        background-color: #ffffff;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
        max-width: 600px;
        width: 100%;
        box-sizing: border-box;
    }

    h1 {
        color: #343a40;
        text-align: center;
        margin-bottom: 30px;
        font-size: 28px;
        font-weight: bold;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    th, td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #dee2e6;
        font-size: 16px;
    }

    th {
        background-color: #007bff;
        color: white;
        font-weight: bold;
    }

    td {
        color: #495057;
    }

    .button-container {
        text-align: center;
        margin-top: 20px;
    }

    .update-button, .back-button {
        padding: 12px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
        text-decoration: none;
        color: white;
        transition: background-color 0.3s ease;
        margin: 5px;
    }

    .update-button {
        background-color: #28a745;
    }

    .update-button:hover {
        background-color: #218838;
    }

    .back-button {
        background-color: #6c757d;
    }

    .back-button:hover {
        background-color: #5a6268;
    }

    p {
        color: #dc3545;
        font-size: 18px;
        text-align: center;
        font-weight: bold;
    }

    @media (max-width: 768px) {
        .container {
            padding: 20px;
        }

        h1 {
            font-size: 24px;
        }

        th, td {
            padding: 10px;
            font-size: 14px;
        }

        .update-button, .back-button {
            width: 100%;
            margin-top: 10px;
        }
    }
</style>
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
