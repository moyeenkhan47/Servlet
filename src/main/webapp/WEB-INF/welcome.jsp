<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
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
    <h1>Welcome, <%= session.getAttribute("userName") %>!</h1>
    <p>Your session will expire in <span id="countdown"></span>.</p>
    
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
