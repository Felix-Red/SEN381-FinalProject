<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Work Order Dashboard</title>
    <link rel="stylesheet" href="Styling/style.css">
    <link rel="stylesheet" href="Styling/workOrder.css">
</head>
<body>
    <!-- Include Header -->
    <div class="header">
        <h1>Work Order Dashboard</h1>
        <p>Overview of all work orders and assignments.</p>
    </div>

    <!-- Table Content -->
    <div class="content">
        <h2>Work Order</h2>
        <table class="work-order-table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Request ID</th>
                    <th>Request Information</th>
                    <th>Requester Name</th>
                    <th>Technician</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<String[]> workOrders = (List<String[]>) request.getAttribute("workOrders");

                    if (workOrders != null && !workOrders.isEmpty()) {
                        int rowNumber = 1;
                        for (String[] workOrder : workOrders) {
                %>
                <tr>
                    <td><%= rowNumber++ %></td>
                    <td><%= workOrder[0] %></td>
                    <td><%= workOrder[1] %></td>
                    <td><%= workOrder[2] %></td>
                    <td><%= workOrder[3] %></td>
                    <td><%= workOrder[4] %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6">No work orders available.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
