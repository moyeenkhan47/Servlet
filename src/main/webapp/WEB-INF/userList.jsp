<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.hrm.Models.RegisterModel" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Admin List</title>

    <link rel="stylesheet" type="text/css" href="styles.css">
   
</head>
<body>
    <form action="index.jsp" method="post">
        <input type="submit" value="Logout" class="logout-button">
    </form>
    <div class="container">
        <h1>Welcome, <%= session.getAttribute("admin") %>!</h1>
        <!-- <div class="username-display">
            <p>Your session will expire in <span id="countdown"></span>.</p>
        </div> -->
        <!-- Search Form -->
        <form action="searchUser" method="get" class="search-form">
            <input type="text" name="searchTerm" placeholder="Search by Name or userId" required>
            <input type="submit" value="Search">
        </form>
        <form action="generatePdf" method="get">
        <button type="submit">Send Report</button>
        <h1>User Details</h1>
        <!-- User Table -->
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>UserName</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Mobile No</th>
                    <th>Register Date</th>
                    <th>Pmail ID</th>
                    <th>Login Time</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<RegisterModel> userList = (List<RegisterModel>) request.getAttribute("alluser");
                if (userList != null) {
                    for (RegisterModel model : userList) {
                %>
                <tr>
                    <td><%= model.getUserId() %></td>
                    <td><%= model.getUserName() %></td>
                    <td><%= model.getEmail() %></td>
                    <td><%= model.getAddress() %></td>
                    <td><%= model.getMobileNo() %></td>
                    <td><%= model.getRegisterDate() %></td>
                    <td><%= model.getPmailId() %></td>
                    <td><%= model.getLoginTime() %></td>
                    <td>
                        <div class="button-container">
                            <a href="updateUser?userId=<%= model.getUserId() %>" class="update-button">Update</a>
                            <a href="searchUser?userId=<%= model.getUserId() %>" class="view-button">View</a>
                            <a href="deleteUser?id=<%= model.getUserId() %>" class="delete-button" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                        </div>
                    </td>
                </tr>
                <%
                    }
                }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
