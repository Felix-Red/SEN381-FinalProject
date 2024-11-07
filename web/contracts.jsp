<%@page import="java.util.List"%>
<%@page import="DataLayer.Contract"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Contract Management</title>
    <link rel="stylesheet" href="Styling/contracts.css">
</head>
<body>
    <h1>Contract Management</h1><br><br>
    <!-- Table to display current contracts -->
    <%
        List<Contract> contracts = (List<Contract>) request.getAttribute("contracts");
        if (contracts != null && !contracts.isEmpty()) {
    %>
        <table>
            <thead>
                <tr>
                    <th>Contract ID</th>
                    <th>Contract Terms</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Renewal Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    for (Contract contract : contracts) {
                %>
                <tr>
                    <td><%= contract.getContractId() %></td>
                    <td><%= contract.getContractTerms() %></td>
                    <td><%= contract.getStartDate() %></td>
                    <td><%= contract.getEndDate() %></td>
                    <td><%= contract.getRenewalDate() %></td>
                    <td>
                        <form method="post" action="ContractServlet">
                            <input type="hidden" name="_method" value="delete">
                            <input type="hidden" name="contractId" value="<%= contract.getContractId() %>">
                            <button type="submit">Terminate</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
            out.println("<p>No active contracts.</p>");
        }
    %> 
    <br><br>
    <!-- Button to add a new contract -->
    <h2>Add New Contract</h2><br>
    <form action="ContractServlet" method="post">
        <%
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
