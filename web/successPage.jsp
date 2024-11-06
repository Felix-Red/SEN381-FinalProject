<%-- 
    Document   : successPage
    Created on : 06 Nov 2024, 16:09:08
    Author     : user-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fa;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 100%;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            font-size: 24px;
            color: #4CAF50;  
            margin-bottom: 20px;
        }

        p {
            font-size: 16px;
            margin-bottom: 15px;
        }

        a {
            text-decoration: none;
            font-size: 16px;
            color: #007bff;
            border: 2px solid #007bff;
            padding: 10px 20px;
            border-radius: 4px;
            transition: all 0.3s ease;
        }

        a:hover {
            background-color: #007bff;
            color: white;
        }

        .message {
            margin: 20px 0;
            padding: 10px;
            background-color: #e7f7e7;
            border: 1px solid #4CAF50;
            border-radius: 5px;
        }
    </style>
    </head>
    <body>
        <h1>Contract Added Successfully</h1>
    <p>Your contract has been successfully added to the system.</p>
    <p><a href="clientDashboard.jsp">Back to Dashboard</a></p>
    </body>
</html>
