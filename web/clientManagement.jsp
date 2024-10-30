<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Client Management</title>
    <link rel="stylesheet" href="clientManagementStyling.css"/>
</head>
<body>
    <!-- Header Section -->
    <header>
        <div class="container">
            <h1>ApexCare Solutions</h1>
            <nav>
                <a href="index.jsp">Home</a>
                <a href="login.jsp">Login</a>
                <a href="registration.jsp">Sign Up</a>
                <a href="#features">Features</a>
                <a href="#contact">Contact</a>
            </nav>
        </div>
    </header>

    <div class="main-container">
        <!-- Add or Edit Client Form -->
        <div class="form-container">
            <h1>Client Management</h1>
            <form action="clientManagementServlet" method="post">
                <input type="hidden" name="clientID" value="${client.clientID}">

                <div class="form-group">
                    <div>
                        <label for="clientName">Client Name:</label>
                        <input type="text" id="clientName" name="clientName" value="${client.clientName}" required>
                    </div>
                    <div>
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" value="${client.email}" required>
                    </div>
                </div>

                <div class="form-group">
                    <div>
                        <label for="phoneNumber">Phone Number:</label>
                        <input type="text" id="phoneNumber" name="phoneNumber" value="${client.phoneNumber}" required>
                    </div>
                    <div>
                        <label for="address">Address:</label>
                        <input type="text" id="address" name="address" value="${client.address}" required>
                    </div>
                </div>

                <div class="form-group">
                    <div>
                        <label for="crm_id">CRM ID:</label>
                        <input type="text" id="crm_id" name="crm_id" value="${client.crm_id}">
                    </div>
                    <div>
                        <label for="service_history">Service History:</label>
                        <input type="text" id="service_history" name="service_history" value="${client.service_history}">
                    </div>
                </div>

                <!-- Dropdown for Business Type -->
                <div class="form-group">
                    <div>
                        <label for="businessType">Business Type:</label>
                        <select id="businessType" name="businessType">
                            <option value="Retail" ${client.businessType == 'Retail' ? 'selected' : ''}>Retail</option>
                            <option value="Finance" ${client.businessType == 'Finance' ? 'selected' : ''}>Finance</option>
                            <option value="Healthcare" ${client.businessType == 'Healthcare' ? 'selected' : ''}>Healthcare</option>
                            <option value="Technology" ${client.businessType == 'Technology' ? 'selected' : ''}>Technology</option>
                            <option value="Other" ${client.businessType == 'Other' ? 'selected' : ''}>Other</option>
                        </select>
                    </div>

                    <!-- Dropdown for Preferred Contact Method -->
                    <div>
                        <label for="preferredContactMethod">Preferred Contact Method:</label>
                        <select id="preferredContactMethod" name="preferredContactMethod">
                            <option value="Email" ${client.preferredContactMethod == 'Email' ? 'selected' : ''}>Email</option>
                            <option value="Phone" ${client.preferredContactMethod == 'Phone' ? 'selected' : ''}>Phone</option>
                    
                        </select>
                    </div>
                </div>

                <!-- Contract Details Section with Dropdowns -->
                <div class="form-group">
                    <div>
                        <label for="contract_start_date">Contract Start Date:</label>
                        <input type="date" id="contract_start_date" name="contract_start_date" value="${client.contract_start_date}">
                    </div>
                    <div>
                        <label for="contract_end_date">Contract End Date:</label>
                        <input type="date" id="contract_end_date" name="contract_end_date" value="${client.contract_end_date}">
                    </div>
                </div>

                <div class="form-group">
                    <!-- Dropdown for Contract Type -->
                    <div>
                        <label for="contract_type">Contract Type:</label>
                        <select id="contract_type" name="contract_type">
                            <option value="Monthly" ${client.contract_type == 'Monthly' ? 'selected' : ''}>Monthly</option>
                            <option value="Yearly" ${client.contract_type == 'Yearly' ? 'selected' : ''}>Yearly</option>
                            <option value="Perpetual" ${client.contract_type == 'Perpetual' ? 'selected' : ''}>Perpetual</option>
                            <option value="Trial" ${client.contract_type == 'Trial' ? 'selected' : ''}>Trial</option>
                        </select>
                    </div>

                    <!-- Dropdown for Contract Status -->
                    <div>
                        <label for="contract_status">Contract Status:</label>
                        <select id="contract_status" name="contract_status">
                            <option value="Active" ${client.contract_status == 'Active' ? 'selected' : ''}>Active</option>
                            <option value="Pending" ${client.contract_status == 'Pending' ? 'selected' : ''}>Pending</option>
                            <option value="Expired" ${client.contract_status == 'Expired' ? 'selected' : ''}>Expired</option>
                            <option value="Cancelled" ${client.contract_status == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div>
                        <label for="contract_value">Contract Value:</label>
                        <input type="text" id="contract_value" name="contract_value" value="${client.contract_value}">
                    </div>
                </div>

                <button type="submit">Add Client</button>
            </form>
        </div>
    </div>

    <!-- Client List Container -->
    <div class="client-list-container">
        <h2>Client List</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                    <th>Contract Start Date</th>
                    <th>Contract End Date</th>
                    <th>Contract Type</th>
                    <th>Contract Value</th>
                    <th>Contract Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="client" items="${clientList}">
                    <tr>
                        <td>${client.clientID}</td>
                        <td>${client.clientName}</td>
                        <td>${client.email}</td>
                        <td>${client.phoneNumber}</td>
                        <td>${client.address}</td>
                        <td>${client.contract_start_date}</td>
                        <td>${client.contract_end_date}</td>
                        <td>${client.contract_type}</td>
                        <td>${client.contract_value}</td>
                        <td>${client.contract_status}</td>
                        <td>
                            <a href="ClientServlet?action=edit&clientID=${client.clientID}">Edit</a> |
                            <a href="ClientServlet?action=delete&clientID=${client.clientID}" onclick="return confirm('Are you sure you want to delete this client?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Footer Section -->
    <footer id="contact">
        <p>© 2024 ApexCare Solutions | <a href="mailto:info@apexcaresolutions.com">info@apexcaresolutions.com</a></p>
    </footer>
</body>
</html>
