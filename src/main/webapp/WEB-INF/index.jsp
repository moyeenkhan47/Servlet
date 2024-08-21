
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Header</title>
   <style type="text/css">
header {
    background-color: #343a40;
    padding: 20px;
    text-align: center;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    color: #ffffff;
}

header h1 {
    margin: 0;
    font-size: 2em;
    font-family: 'Arial', sans-serif;
}

header nav ul {
    list-style-type: none;
    padding: 0;
    margin: 10px 0 0;
}

header nav ul li {
    display: inline-block; /* Use inline-block for better control */
    margin-right: 20px;
}

header nav ul li a {
    text-decoration: none;
    color: #17a2b8;
    font-weight: bold;
    font-family: 'Arial', sans-serif;
}

header nav ul li a:hover {
    text-decoration:none;
    color: #ffffff;
    background-color: #17a2b8;
    padding: 5px 10px;
    border-radius: 4px;
    transition: all 0.3s ease;
}

/* Responsive design for smaller screens */
@media (max-width: 768px) {
    header {
        text-align: left;
        padding: 15px;
    }

    header h1 {
        font-size: 1.5em;
    }

    header nav ul {
        margin-top: 15px;
        text-align: center;
    }

    header nav ul li {
        display: block; /* Stack menu items vertically */
        margin: 10px 0;
    }

    header nav ul li a {
        display: block; /* Make links take full width */
        padding: 10px 15px;
        font-size: 1em;
    }
}

/* Further adjustments for very small screens */
@media (max-width: 480px) {
    header {
        padding: 10px;
    }

    header h1 {
        font-size: 1.2em;
    }

    header nav ul li a {
        font-size: 0.9em;
        padding: 8px 10px;
    }
}

   </style>
</head>
<body>
    <header>
        <h1>My Application</h1>
        <nav>
            <ul>
                <li><a href="login.jsp">Student Details</a></li>
                <li><a href="adminlogin.jsp">Admin</a></li>
                <li><a href="register.jsp">SignUp</a></li>
            </ul>
        </nav>
    </header>
</body>
</html>
