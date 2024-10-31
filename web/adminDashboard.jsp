<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="Styling/adminDashboard.css">
    </head>
    <body>
         <div class="container">
        <!-- Sidebar -->
        <div class="sidebar">
            <img src="Styling/Updated_ApexCare_Solutions_Logo.png" alt="Profile Picture" class="sidebar-image">
            <h2>Menu</h2>
            <ul>
                <li><a href="adminDashboard.jsp">DashBoard</a></li>
                <li><a href="workOrder.jsp" onclick="loadContent('workOrder.jsp'); return false;">Work order</a></li>
                <li><a href="displayRequests.jsp" onclick="loadContent('displayRequests.jsp'); return false;">Requests</a></li>
                <li><a href="Technicians.jsp" onclick="loadContent('Technicians.jsp'); return false;">Technicians</a></li>
                 <li><a href="Technicians.jsp" onclick="loadContent('Technicians.jsp'); return false;">Technicians</a></li>
            </ul>
        </div>
        
         <!-- Main Content -->
        <div class="main-content" id="main-content">
            <!-- Header -->
            <div class="header">
                <h1>Dashboard</h1>
                <p>Overview of requests, technicians, and customers.</p>
            </div>
            
            <!-- Counters Section -->
            <div class="counters">
                <div class="counter-box">
                    <div class="icon">&#128230;</div> <!-- Example icon for New Request -->
                    <h2 id="newRequestCount">0</h2>
                    <p>New Request</p>
                    <small>No of New Request</small>
                </div>
                
                <div class="counter-box">
                    <div class="icon">&#128196;</div> <!-- Example icon for Request Approved -->
                    <h2 id="requestApprovedCount">0</h2>
                    <p>Request Approved</p>
                    <small>No of Request Approved</small>
                </div>
                
                <div class="counter-box">
                    <div class="icon">&#128100;</div> <!-- Example icon for Total Technician -->
                    <h2 id="totalTechnicianCount">0</h2>
                    <p>Total Technician</p>
                    <small>Total No of Technician</small>
                </div>
                
                <div class="counter-box">
                    <div class="icon">&#128101;</div> <!-- Example icon for Total Customer -->
                    <h2 id="totalCustomerCount">0</h2>
                    <p>Total Customer</p>
                    <small>Total No of Customers</small>
                </div>
            </div>
        </div>
    </div>
    
    <script src="script.js"></script>
    </body>
</html>

