<%-- 
    Document   : clientDashboard
    Created on : 31 Oct 2024, 14:13:27
    Author     : user-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ApexCare Solutions Dashboard</title>
        <link rel="stylesheet" href="Styling/clientDashboard.css"/>
        <script src="https://unpkg.com/@phosphor-icons/web"></script>
    </head>
    <body>
       <div class="container">
        <aside class="sidebar">
            <div class="sidebar-header">
                <img src="Styling/Updated_ApexCare_Solutions_Logo.png" alt="ApexCare Logo" class="sidebar-image">
                <h2>ApexCare Solutions</h2>
            </div>
            <% 
                //HttpSession session = request.getSession();
                String clientName = (String) session.getAttribute("clientName");
                if (clientName == null) {
                    clientName = "Guest"; // Default name if not logged in
                }
            %>
            <h3><i class="ph ph-hand-waving"></i> Welcome, <span id="clientName"><%= clientName %></span></h3>
            <nav>
                    <li> <a href="#" onclick="loadContent('overview.jsp')"><i class="ph ph-user"></i> Overview</a></li>
                    <li> <a href="#" onclick="loadContent('ContractServlet')"><i class="ph ph-address-book-tabs"></i> My Contracts</a></li>
                    <li><a href="#" onclick="loadContent('serviceRequests.jsp')"> <i class="ph ph-paper-plane-tilt"></i> Service Requests</a></li>
                    <li><a href="#" onclick="loadContent('clientSatisfaction.jsp')"><i class="ph ph-smiley"></i> Customer Satisfaction</a></li>
                    <li><a href="index.jsp"><i class="ph ph-sign-out"></i> Logout</a></li>
            </nav>
        </aside>
            
        <main class="content">
            <div id="main-content">
                <h3>Welcome to the dashboard</h3>
            </div>
        </main>
    </div>
            <script src="script.js"></script>
    </body>
</html>
