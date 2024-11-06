<%-- 
    Document   : contracts
    Created on : 05 Nov 2024, 11:24:45
    Author     : user-pc
--%>

<%@page import="java.util.List"%>
<%@page import="DataLayer.Contract"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        input[type="button"], input[type="submit"] {
            padding: 10px;
            margin: 5px;
            cursor: pointer;
        }
    </style>
    </head>
    <body>
        <h1>Contract Management</h1><br><br>

<!-- Table to display current contracts -->
<%
    List<Contract> contracts = (List<Contract>) request.getAttribute("contracts");
    if (contracts != null && !contracts.isEmpty()) {
%>
        <table>
            <tr>
                <th>Contract ID</th>
                <th>Contract Terms</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Renewal Date</th>
            </tr>
            <% 
                for (Contract contract : contracts) {
            %>
                <tr>
                    <td><%= contract.getContractId() %></td>
                    <td><%= contract.getContractTerms() %></td>
                    <td><%= contract.getStartDate() %></td>
                    <td><%= contract.getEndDate() %></td>
                    <td><%= contract.getRenewalDate() %></td>
                </tr>
            <%
                }
            %>
        </table>
<%
    } else {
        out.println("No active contracts.");
    }
%><br><br>

<!-- Button to add a new contract -->
<h2>Add New Contract</h2><br>
<form action="ContractServlet" method="post">
        <%
            //HttpSession session = request.getSession();
            Integer clientId = (Integer) session.getAttribute("clientId"); 
            if (clientId != null) {
        %>
            <input type="hidden" name="clientId" value="<%= clientId %>">
        <% } else { %>
            <p>Error: Client ID not found in session. Please log in.</p>
        <% } %>
        
        <label for="contractTerms">Contract Terms:</label><br>
        <textarea id="contractTerms" name="contractTerms" rows="4" cols="50" required></textarea><br><br>

        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" required><br><br>

        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" required><br><br>

        <input type="submit" value="Add Contract">
    </form>

    </body>
</html>
