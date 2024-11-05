<%-- 
    Document   : contracts
    Created on : 05 Nov 2024, 11:24:45
    Author     : user-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Contract Management</h1>

<!-- Table to display current contracts -->
<table>
    <thead>
        <tr>
            <th>Contract ID</th>
            <th>Client ID</th>
            <th>Contract Terms</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Renewal Date</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
       
    </tbody>
</table>

<!-- Button to add a new contract -->
<h2>Add New Contract</h2>
<form action="CreateContractServlet" method="post">
    <label for="clientId">Client ID:</label>
    <input type="number" id="clientId" name="clientId" required><br><br>

    <label for="contractTerms">Contract Terms:</label><br>
    <textarea id="contractTerms" name="contractTerms" rows="4" cols="50" required></textarea><br><br>

    <label for="startDate">Start Date:</label>
    <input type="date" id="startDate" name="startDate" required><br><br>

    <label for="endDate">End Date:</label>
    <input type="date" id="endDate" name="endDate" required><br><br>

    <label for="renewalDate">Renewal Date:</label>
    <input type="date" id="renewalDate" name="renewalDate" required><br><br>

    <input type="submit" value="Add Contract">
</form>

    </body>
</html>
