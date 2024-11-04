<%-- 
    Document   : clientDashboard
    Created on : 31 Oct 2024, 14:13:27
    Author     : user-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ApexCare Solutions Dashboard</title>
    </head>
    <body>
        <div class="container">
        <!-- Sidebar Navigation -->
        <aside class="sidebar">
            <h2>ApexCare Dashboard</h2>
            <nav>
                <ul>
                    <li><a href="#overview" class="active">Overview</a></li>
                    <li><a href="#contracts">My Contracts</a></li>
                    <li><a href="#requests">Service Requests</a></li>
                    <li><a href="#history">Service History</a></li>
                    <li><a href="#settings">Account Settings</a></li>
                </ul>
            </nav>
        </aside>

        <!-- Main Content Area -->
        <div class="main-content">
            <header class="header">
                <h1>Welcome, [Client's Name]</h1>
            </header>

            <!-- Overview Section -->
            <section id="overview" class="section">
                <h2>Dashboard Overview</h2>
                <div class="card-container">
                    <div class="card">
                        <h3>Active Contracts</h3>
                        <p>2 active contracts</p>
                    </div>
                    <div class="card">
                        <h3>Open Service Requests</h3>
                        <p>3 requests in progress</p>
                    </div>
                    <div class="card">
                        <h3>Recent Services</h3>
                        <p>5 recent services completed</p>
                    </div>
                </div>
            </section>

            <!-- Contracts Section -->
            <section id="contracts" class="section">
                <h2>My Contracts</h2>
                <p>Details about active service contracts...</p>
                <!-- Add content as needed -->
            </section>

            <!-- Service Requests Section -->
            <section id="requests" class="section">
                <h2>Service Requests</h2>
                <p>Manage ongoing and past service requests...</p>
                <!-- Add content as needed -->
            </section>

            <!-- Service History Section -->
            <section id="history" class="section">
                <h2>Service History</h2>
                <p>View your service history and details...</p>
                <!-- Add content as needed -->
            </section>

            <!-- Account Settings Section -->
            <section id="settings" class="section">
                <h2>Account Settings</h2>
                <p>Update personal details and preferences...</p>
                <!-- Add content as needed -->
            </section>
        </div>
    </div>
    </body>
</html>
