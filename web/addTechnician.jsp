<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DataLayer.Technicians" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= request.getAttribute("technician") == null ? "Add Technician" : "Edit Technician" %></title>
    <link rel="stylesheet" href="Styling/addTechnician.css">
    <link rel="stylesheet" href="Styling/adminDashboard.css">
</head>
<body>
    <div class="header">
        <h1><%= request.getAttribute("technician") == null ? "Add Technician" : "Edit Technician" %></h1>
    </div>
    <div class="form-container">
        <h2 class="form-title"><%= request.getAttribute("technician") == null ? "Add Technician" : "Edit Technician" %></h2>
        <form class="technician-form" action="<%= request.getAttribute("technician") == null ? "addTechnicianServlet" : "UpdateTechnicianServlet" %>" method="post">
            <!-- Hidden field for technician ID (only if editing) -->
            <%
                Technicians technician = (Technicians) request.getAttribute("technician");
                if (technician != null) {
            %>
            <input type="hidden" name="technicianId" value="<%= technician.getId() %>">
            <% } %>

            <div class="form-group">
                <label for="technicianName">Technician Name</label>
                <input type="text" id="technicianName" name="technicianName" value="<%= technician != null ? technician.getName() : "" %>" required>
            </div>

            <div class="form-group">
                <label for="techniciancity">City</label>
                <input type="text" id="techniciancity" name="techniciancity" value="<%= technician != null ? technician.getTechnicianCity() : "" %>" required>
            </div>

            <div class="form-group-inline">
                <div class="form-group">
                    <label for="mobile">Mobile</label>
                    <input type="text" id="mobile" name="mobile" value="<%= technician != null ? technician.getTechnicianMobile() : "" %>" required>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="<%= technician != null ? technician.getTechnicianEmail() : "" %>" required>
                </div>
            </div>

            <button type="submit" class="btn-submit"><%= technician == null ? "Submit" : "Update" %></button>
        </form>
    </div>
    <script src="script.js"></script>
</body>
</html>
