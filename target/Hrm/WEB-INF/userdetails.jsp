<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.hrm.Models.RegisterModel" %>
<%@ page import="java.util.List" %>

<html>
<head>

    <title>User List</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script type="text/javascript">
        function startCountdown(seconds) {
            function updateCountdown() {
                var countdownElement = document.getElementById("countdown");
                if (seconds > 0) {
                    seconds--;
                    var minutes = Math.floor(seconds / 60);
                    var remainingSeconds = seconds % 60;
                    countdownElement.innerHTML = minutes + " minute(s) " + remainingSeconds + " second(s)";
                } else {
                    countdownElement.innerHTML = "Session expired!";
                    window.location.href = "login.jsp"; // Redirect to login page
                }
            }
            setInterval(updateCountdown, 1000);
        }

        function checkSession() {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "checkSession", true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4) {
                    if (xhr.status == 401) {
                        alert("Session expired! You will be redirected to the login page.");
                        window.location.href = "login.jsp";
                    }
                }
            };
            xhr.send();
        }

        function startSessionCheck() {
            setInterval(checkSession, 60000); // Check session every minute
        }
    </script>
</head>
<body onload="startCountdown(<%= session.getMaxInactiveInterval() %>); startSessionCheck();">
    <form action="logout" method="post">
        <input type="submit" value="Logout" class="logout-button">
    </form>
    <div class="container">
        <h1>Welcome, <%= session.getAttribute("userName") %>!</h1>
        <div class="username-display">
            <p>Your session will expire in <span id="countdown"></span>.</p>
        </div>
        <!-- Search Form -->
        <form action="searchUser" method="get" class="search-form">
            <input type="text" name="searchTerm" placeholder="Search by Name or userId" required>
            <input type="submit" value="Search">
        </form>
        <h1>User Details</h1>
        <!-- User Table -->
        <table>
            <tr>
                <th>ID</th>
                <th>UserName</th>
                <th>Email</th>
                <th>Address</th>
                <th>Mobile No</th>
                <th>Register Date</th>
                <th>Pmail ID</th>
              
            </tr>
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
               
            </tr>
            <%
                }
            }
            %>
        </table>
    </div>
</body>
</html>
