<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StudentLogin</title>
    <style>
       body {
    font-family: Arial, sans-serif;
    background-color: #e9ecef;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
    text-align: center;
}

h2 {
    color: #333;
    margin-top: 0;
    font-size: 24px;
    font-weight: bold;
}

form {
    background-color: #ffffff;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 400px;
    box-sizing: border-box;
    margin-top: 20px;
}

label {
    display: block;
    margin-bottom: 10px;
    color: #555;
    font-size: 14px;
    font-weight: bold;
}

input[type="text"], input[type="password"] {
    width: 100%;
    padding: 12px;
    margin-bottom: 20px;
    border: 1px solid #ced4da;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 16px;
}

button {
    width: 100%;
    padding: 12px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s ease;
    margin-bottom: 15px;
}

button:hover {
    background-color: #218838;
}

.register-button {
    background-color: #007bff;
}

.register-button:hover {
    background-color: #0056b3;
}

.error-message {
    color: red;
    margin-top: 10px;
    font-size: 14px;
    font-weight: bold;
}

.form-footer {
    margin-top: 20px;
    text-align: center;
}

@media (max-width: 768px) {
    form {
        width: 90%;
        padding: 20px;
    }

    button {
        padding: 10px;
    }

    input[type="text"], input[type="password"] {
        padding: 10px;
    }
}

    </style>
</head>
<body>
    <h2>Login For Students</h2>
    <form action="login" method="post">
        <label for="email">User Email:</label>
        <input type="text" id="email" name="email" placeholder="Enter your email" required>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="Enter your password" required>
        
        <button type="submit">Login</button>
        <button type="button" class="register-button" onclick="window.location.href='register.jsp';">Register</button>
        <button type="button" class="back-button" onclick="window.location.href='index.jsp';">Back</button>
    </form>
    <div>
        <% 
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
                out.println("<p class='error-message'>" + errorMessage + "</p>");
            }
        %>
    </div>
</body>
</html>
