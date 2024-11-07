<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="DataLayer.Technicians"%>
<%@ page import="BusinessLogic.TechnicianServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="Styling/adminDashboard.css">
        <link rel="stylesheet" href="Styling/technicians.css">
        
    </head>
    <body>
    <div class="header">
        <h1>Technicians</h1>
        <p></p>
    </div>
        <!-- Header with buttons -->
    <div class="TechBTN">
        
        <button class="btn-primary" onclick="loadContent('addTechnician.jsp'); return false;">Add Technician</button>
    </div>
     <!-- Technician Details Table -->
    <div class="content">
        <h2>Technician Details</h2>
        <table class="technician-table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Technician ID</th>
                    <th>Technician Name</th>
                    <th>Technician City</th>
                    <th>Technician Mobile</th>
                    <th>Technician Email</th>
                    <th>Action</th>
                </tr>
            </thead>
          <tbody>
                <%
                    ArrayList<Technicians> technicianList = (ArrayList<Technicians>) request.getAttribute("technicianList");
                    if (technicianList == null) {
                        out.println("<tr><td colspan='7'>technicianList attribute is null. Please check if the servlet has set the attribute correctly.</td></tr>");
                    } else if (technicianList.isEmpty()) {
                        out.println("<tr><td colspan='7'>No technicians found in the database.</td></tr>");
                    } else {
                        int rowNumber = 1;
                        for (Technicians technician : technicianList) {
                %>
                <tr>
                    <td><%= rowNumber++ %></td>
                    <td><%= technician.getId() %></td>
                    <td><%= technician.getName() %></td>
                    <td><%= technician.getTechnicianCity() %></td>
                    <td><%= technician.getTechnicianMobile() %></td>
                    <td><%= technician.getTechnicianEmail() %></td>
                    <td>
                        <a href="EditTechnicianServlet?id=<%= technician.getId() %>" class="btn-edit">Edit</a>
                        <a href="DeleteTechnicianServlet?id=<%= technician.getId() %>" class="btn-delete" onclick="return confirm('Are you sure you want to delete this technician?');">Delete</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
    </body>
</html>
