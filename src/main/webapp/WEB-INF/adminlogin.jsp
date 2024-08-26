<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>
    <style>
       /* styles.css */
body {
    font-family: 'Roboto', sans-serif;
    background-color: #f4f7f9;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    text-align: center;
}

.container {
    width: 100%;
    max-width: 380px;
    background: #ffffff;
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
    padding: 30px;
    box-sizing: border-box;
}

h2 {
    color: #333;
    margin-bottom: 25px;
    font-size: 24px;
    font-weight: 700;
    letter-spacing: 1px;
}

label {
    display: block;
    margin-bottom: 8px;
    color: #444;
    font-size: 15px;
    font-weight: 500;
    text-align: left;
}

input[type="text"], input[type="password"] {
    width: 100%;
    padding: 12px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
    font-size: 15px;
    transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

input[type="text"]:focus, input[type="password"]:focus {
    border-color: #007bff;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    outline: none;
}

button {
    width: 100%;
    padding: 14px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 15px;
    transition: background-color 0.3s ease;
    margin-top: 10px;
    font-weight: bold;
}

button:hover {
    background-color: #0056b3;
}

.back-button {
    background-color: #6c757d;
}

.back-button:hover {
    background-color: #5a6268;
}

.error-message {
    color: red;
    margin-top: 15px;
    font-size: 13px;
    font-weight: bold;
}

@media (max-width: 480px) {
    .container {
        padding: 20px;
    }

    button {
        padding: 12px;
        font-size: 14px;
    }

    input[type="text"], input[type="password"] {
        padding: 10px;
        font-size: 14px;
    }

    h2 {
        font-size: 22px;
    }
}

    </style>
</head>
<body>
    <div class="container">
        <form action="adminLogin" method="post">
            <h2>Admin Login</h2>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="Enter admin username"  required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter admin password" required>

            <button type="submit">Login</button>
            <button type="button" class="back-button" onclick="window.location.href='index.jsp';">Back</button>
            <div>
                <% 
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                        out.println("<p class='error-message'>" + errorMessage + "</p>");
                    }
                %>
            </div>
        </form>
    </div>
</body>
</html>
