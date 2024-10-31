<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client Login</title>
        <link rel="stylesheet" href="Styling/clientLoginStyles.css">
    </head>
    <body>
        <div class="login-container">
        <h2>Client Login</h2>

        <form action="ClientLoginServlet" method="post">
            <label for="email">Email: </label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Login</button>
        </form>

        
        <p class="employee-login-link">
            <a href="employeeLogin.jsp">Employee Login</a>
        </p>
        
        <p class="register-link">
            Not a member? <a href="registration.jsp">Register here</a>
        </p>
    </div>

    </body>
</html>
