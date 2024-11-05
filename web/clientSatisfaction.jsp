<%-- 
    Document   : clientSatisfaction
    Created on : 05 Nov 2024, 12:44:28
    Author     : user-pc
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
        function showSuccessMessage(message) {
            alert(message);
        }
    </script>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        color: #333;
        margin: 0;
        padding: 20px;
    }

    h1 {
        color: #4CAF50; /* Green color for the main heading */
        text-align: center;
    }

    h2 {
        color: #333; /* Dark gray for subheadings */
        text-align: center;
    }

    form {
        background-color: white;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        max-width: 600px;
        margin: 20px auto; /* Center the form */
    }

    label {
        display: block;
        margin: 10px 0 5px; /* Margin for spacing */
    }

    select,
    textarea {
        width: 100%; /* Full width for inputs */
        padding: 10px;
        margin-bottom: 15px; /* Space below each input */
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box; /* Include padding in width */
    }

    input[type="submit"] {
        background-color: #4CAF50; /* Green background for the button */
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        transition: background-color 0.3s; /* Smooth transition */
        width: 100%; /* Full width for submit button */
    }

    input[type="submit"]:hover {
        background-color: #45a049; /* Darker green on hover */
    }
</style>

    </head>
    <body>
     
        <h1>Customer Satisfaction</h1><br>

        <h2>Submit Your Feedback</h2><br>
    <form action="SubmitFeedbackServlet" method="post">
        <label for="satisfaction">Rate your satisfaction (1 to 5):</label><br>
        <select id="satisfaction" name="satisfaction">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select><br><br>

        <label for="comments">Additional Comments:</label><br>
        <textarea id="comments" name="comments" rows="4" cols="50"></textarea><br><br>
        
        <input type="hidden" name="clientId" value="<%= session.getAttribute("clientId") != null ? session.getAttribute("clientId") : 0 %>">

        <input type="submit" value="Submit Feedback">
    </form>

   <%
        String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
            session.removeAttribute("successMessage"); 
    %>
            <script>
                showSuccessMessage("<%= successMessage %>");
            </script>
    <%
        }
    %>
    
    </body>
</html>
