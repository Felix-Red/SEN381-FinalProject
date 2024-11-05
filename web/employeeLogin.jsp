<%-- 
    Document   : employeeLogin
    Created on : 31 Oct 2024, 12:06:33
    Author     : user-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Login</title>
        <link rel="stylesheet" href="Styling/employeeLoginStyles.css">
    </head>
    <body>
        <div class="login-container">
            <h2>Employee Login</h2>
            
            <!-- Error message display -->
            <% 
                String errorMessage = (String) request.getAttribute("errorMessage"); 
                if (errorMessage != null) { 
            %>
                <div class="error-message" style="color: red; margin-bottom: 10px;">
                    <%= errorMessage %>
                </div>
            <% 
                } 
            %>

            <form action="EmployeeLoginServlet" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>

                <button type="submit">Login</button>
            </form>

            <p class="client-login-link">
                <a href="clientLogin.jsp">Client Login</a>
            </p>
        </div>
    </body>
</html>
