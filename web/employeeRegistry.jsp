<%-- 
    Document   : registration
    Created on : 29 Oct 2024, 11:34:11
    Author     : felix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link rel="stylesheet" href="Styling/employeeRegistry.css">
        
    </head>
    <body>
        <div class="container">
        <h1>ApexCare Employee Registration</h1>
        <form action="RegisterServlet" method="post" class="registration-form">
            <!-- Basic Client Details -->
            <div class="form-section">
                <h2>Basic Information</h2>
                <div class="grid-container">
                    <div class="form-row">
                        <label for="name">Full Name:</label>
                        <input type="text" id="name" name="name" required>
                    </div>
                    <div class="form-row">
                        <label for="company">Company Name:</label>
                        <input type="text" id="company" name="company">
                    </div>
                    <div class="form-row">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                    <div class="form-row">
                        <label for="phone">Phone Number:</label>
                        <input type="tel" id="phone" name="phone" required>
                    </div>
                    <div class="form-row">
                        <label for="address">Address:</label>
                        <input type="text" id="address" name="address">
                    </div>
                </div>
            </div>

            <!-- Contract and Service Details -->
            <div class="form-section">
                <h2>Contract Details</h2>
                <div class="grid-container">

                    <div class="form-row">
                        <label for="startDate">Contract Start Date:</label>
                        <input type="date" id="startDate" name="startDate" required>
                    </div>
                    <div class="form-row">
                        <label for="endDate">Contract End Date:</label>
                        <input type="date" id="endDate" name="endDate">
                    </div>
                </div>
            </div>

            <!-- Login Details -->
            <div class="form-section">
                <h2>Login Details</h2>
                <div class="grid-container">
                    <div class="form-row">
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    <div class="form-row">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <div class="form-row">
                        <label for="confirmPassword">Confirm Password:</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" required>
                    </div>
                </div>
            </div>
            <input type="submit" value="Register" class="submit-button">
        </form>
    </div>
    </body>
</html>
