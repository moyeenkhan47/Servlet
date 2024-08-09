<%@page import="com.hrm.Models.RegisterModel"%>
<%@page import="java.util.List"%>

<html>
<head>
    <title>User List</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="container">
    <h1>User List</h1>
    <!-- Search Form -->
    <form action="searchUser" method="get">
        <div>
            <input type="text" name="searchTerm" placeholder="Search by Name or User ID" />
            <input type="submit" value="Search" />
        </div>
    </form>
    <!-- User Table -->
    <table>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Address</th>
            <th>Mobile No</th>
            <th>Register Date</th>
            <th>Pmail ID</th>
            <th>Actions</th>
        </tr>
        <%
        List<RegisterModel> alluser = (List<RegisterModel>) request.getAttribute("alluser");
            if (alluser != null) {
                for (RegisterModel model : alluser) {
        %>
        <tr>
            <td><%= model.getUserId() %></td>
            <td><%= model.getUserName() %></td>
            <td><%= model.getEmail() %></td>
            <td><%= model.getAddress() %></td>
            <td><%= model.getMobileNo() %></td>
            <td><%= model.getRegisterDate() %></td>
            <td><%= model.getPmailId() %></td>
            <td>
                <a href="updateUser?userId=<%= model.getUserId() %>" class="update-button">Update</a>
                <a href="deleteUser?id=<%= model.getUserId() %>" class="delete-button" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <div class="button-container">
        <button class="back-button" onclick="window.history.back();">Back</button>
    </div>
</div>
</body>
</html>
