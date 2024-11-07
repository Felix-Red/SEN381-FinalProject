<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="Styling/adminDashboard.css">
        <script src="https://unpkg.com/@phosphor-icons/web"></script>
    </head>
    <body>
         <div class="container">
        <!-- Sidebar -->
        <div class="sidebar">
            <img src="Styling/Updated_ApexCare_Solutions_Logo.png" alt="Profile Picture" class="sidebar-image">
            <h2>Menu</h2>
            <ul>
                <li><a href="DashboardServlet"> <i class="ph ph-desktop"></i>   DashBoard</a></li>
                <li><a href="workOrder.jsp" onclick="loadContent('WorkOrderServlet'); return false;"> <i class="ph ph-hourglass-medium"></i>    Work order</a></li>
                <li><a href="AssignTechnicianServlet" onclick="loadContent('displayRequests.jsp'); return false;"> <i class="ph ph-hand-arrow-down"></i>    Requests</a></li>
                <li><a href="TechniciansServlet" onclick="loadContent('TechniciansServlet'); return false;"> <i class="ph ph-screwdriver"></i>  Technicians</a></li>
                <li><a href="employeeRegistry.jsp" onclick="loadContent('employeeRegistry.jsp'); return false;"> <i class="ph ph-read-cv-logo"></i> Register Employee</a></li>
                <li><a href="FeedbackServlet" onclick="loadContent('FeedbackServlet'); return false;"> <i class="ph ph-star-half"></i>  FeedBack</a></li>
                 <li><a href="index.jsp"><i class="ph ph-sign-out"></i> Logout</a></li>
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
                    <h2 id="newRequestCount">
                        <%= request.getAttribute("newRequestCount") != null ? request.getAttribute("newRequestCount") : 0 %>
                    </h2>
                    <p>New Request</p>
                    <small>No of New Requests</small>
                </div>
                
                <div class="counter-box">
                    <div class="icon">&#128196;</div> <!-- Example icon for Request Approved -->
                    <h2 id="requestApprovedCount">
                        <%= request.getAttribute("requestApprovedCount") != null ? request.getAttribute("requestApprovedCount") : 0 %>
                    </h2>
                    <p>Request Approved</p>
                    <small>No of Request Approved</small>
                </div>
                
                <div class="counter-box">
                    <div class="icon">&#128100;</div> <!-- Example icon for Total Technician -->
                    <h2 id="totalTechnicianCount">
                        <%= request.getAttribute("totalTechnicianCount") != null ? request.getAttribute("totalTechnicianCount") : 0 %>
                    </h2>
                    <p>Total Technician</p>
                    <small>Total No of Technicians</small>
                </div>
                
                <div class="counter-box">
                    <div class="icon">&#128101;</div> <!-- Example icon for Total Customer -->
                    <h2 id="totalCustomerCount">
                        <%= request.getAttribute("totalCustomerCount") != null ? request.getAttribute("totalCustomerCount") : 0 %>
                    </h2>
                    <p>Total Customer</p>
                    <small>Total No of Customers</small>
                </div>
            </div>
        </div>
    </div>
    
    <script src="script.js"></script>
    </body>
</html>

