
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Header</title>
   <style type="text/css">
   header {
    background-color: #f8f9fa;
    padding: 10px;
    text-align: center;
}

header nav ul {
    list-style-type: none;
    padding: 0;
}

header nav ul li {
    display: inline;
    margin-right: 10px;
}

header nav ul li a {
    text-decoration: none;
    color: #007bff;
}

header nav ul li a:hover {
    text-decoration: underline;
}
   </style>
</head>
<body>
    <header>
        <h1>My Application</h1>
        <nav>
            <ul>
                <li><a href="userDetails">UserDetails</a></li>
                <li><a href="register">Admin</a></li>
                <li><a href="<c:url value='contact.jsp'/>">Contact</a></li>
            </ul>
        </nav>
    </header>
</body>
</html>
