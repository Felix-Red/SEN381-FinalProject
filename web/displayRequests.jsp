<%@ page import="java.util.List, java.util.ArrayList" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet" %>
<%@ page import="DataLayer.Request, DataLayer.Technicians, DataLayer.DBConnection" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Assign Technician</title>
    <link rel="stylesheet" href="Styling/adminDashboard.css">
    <link rel="stylesheet" href="Styling/displayRequests.css">
</head>
<body>
    <div class="header">
        <h1>Requests Dashboard</h1>
        <p>Overview of all client requests</p>
    </div>
    <div class="content">
        <% 
            // Get the technician list from the database
            List<Technicians> technicianList = new ArrayList<>();
            try (Connection conn = DBConnection.getConnection()) {
                String sqlTechnicians = "SELECT technicianid, technicianname FROM tbltechnicians";
                PreparedStatement stmtTechnicians = conn.prepareStatement(sqlTechnicians);
                ResultSet rsTechnicians = stmtTechnicians.executeQuery();

                while (rsTechnicians.next()) {
                    Technicians technician = new Technicians(
                            rsTechnicians.getInt("technicianid"),
                            rsTechnicians.getString("technicianname"),
                            "", "", "" // Skipping other fields not used here
                    );
                    technicianList.add(technician);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>

        <table class="technician-table">
            <thead>
                <tr>
                    <th>Request ID</th>
                    <th>Client ID</th>
                    <th>Request Details</th>
                    <th>Assigned Technician</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Request> requestList = new ArrayList<>();
                    try (Connection conn = DBConnection.getConnection()) {
                        String sqlRequests = "SELECT request_id, client_id, description, technician_id FROM requests WHERE technician_id IS NULL";
                        PreparedStatement stmtRequests = conn.prepareStatement(sqlRequests);
                        ResultSet rsRequests = stmtRequests.executeQuery();

                        while (rsRequests.next()) {
                            Request req = new Request(
                                    rsRequests.getInt("request_id"),
                                    rsRequests.getInt("client_id"),
                                    rsRequests.getString("description"),
                                    rsRequests.getInt("technician_id")
                            );
                            requestList.add(req);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (requestList != null && !requestList.isEmpty()) {
                        for (Request req : requestList) {
                %>
                <tr>
                    <td><%= req.getRequestId() %></td>
                    <td><%= req.getClientId() %></td>
                    <td><%= req.getRequestDetails() %></td>
                    <td><%= req.getTechnicianId() == 0 ? "Not Assigned" : req.getTechnicianId() %></td>
                    <td>
                        <form id="assignForm_<%= req.getRequestId() %>" onsubmit="assignTechnician(event, <%= req.getRequestId() %>)">
                            <select name="technicianId" id="technicianId_<%= req.getRequestId() %>" required>
                                <option value="">Select Technician</option>
                                <%
                                    for (Technicians tech : technicianList) {
                                %>
                                <option value="<%= tech.getId() %>"><%= tech.getName() %></option>
                                <%
                                    }
                                %>
                            </select>
                            <button type="submit">Assign</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5">No requests available.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
    
    <script>
        function assignTechnician(event, requestId) {
            event.preventDefault(); // Prevent the default form submission
            
            const technicianId = document.getElementById(`technicianId_${requestId}`).value;

            if (technicianId === "") {
                alert("Please select a technician");
                return;
            }

            // Use fetch to send an AJAX request to update the assignment
            fetch('displayRequests.jsp', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `requestId=${requestId}&technicianId=${technicianId}`
            })
            .then(response => {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error('Failed to assign technician');
                }
            })
            .then(data => {
                // Refresh the content dynamically
                loadContent('displayRequests.jsp');
                alert('Technician assigned successfully!');
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Failed to assign technician.');
            });
        }

        // Function to load the content dynamically while keeping the sidebar intact
        function loadContent(page) {
            fetch(page)
                .then(response => response.text())
                .then(data => {
                    document.getElementById('main-content').innerHTML = data;
                })
                .catch(error => console.error('Error loading content:', error));
        }
    </script>
</body>
</html>
