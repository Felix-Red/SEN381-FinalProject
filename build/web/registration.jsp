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
        <link rel="stylesheet" href="Styling/registrationStyles.css">
    </head>
    <body>
        <div class="container">
            <h1>Schedule your appointment</h1>
            <form action="submitAppointment" method="post">
                <label for="name">Name: </label>
                <input type="text" id="name" name="name" required><br>
                <label for="email">Email: </label>
                <input type="email" id="email" name="email" required><br>
                <label for="date">Appointment Date: </label>
                <input type="date" id="date" name="date" required><br>
                <label for="serviceType">Service Type: </label>
                <input type="text" id="serviceType" name="serviceType" required><br>
                <label for="comments">Comments: </label>
                <textarea id="commemts" name="comments"></textarea><br>
                
                <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>
