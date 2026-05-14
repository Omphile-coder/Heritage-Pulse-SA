<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!DOCTYPE html>
<html>
<head>
    <title>Heritage Pulse SA | Login</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

    <div class="remedy-card" >
        <h2>Portal Login</h2>
        <p>Access the Cultural Guardian Archive</p>

        <c:if test="${not empty error}">
            <p style="color: #c62828; background: #ffebee; padding: 10px; border-radius: 5px;">${error}</p>
        </c:if>

        <form action="LoginServlet.do" method="POST">
            <label>Email Address</label>
            <input type="email" name="email" required >

            <label>Password</label>
            <input type="password" name="password" required >

            <button type="submit" class="btn-search" >Login</button>
        </form>
            
            <p>Don't have an account? </p>
            <p>
            <a href="register.jsp">Create Account</a>
    </div>

</body>
</html>