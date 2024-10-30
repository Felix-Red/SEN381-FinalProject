<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <<link rel="stylesheet" href="clientManagementStyling.css"/>>
    </head>
    <body>
       <div class="main-container">
        <!-- Add or Edit Client Form -->
        <div class="form-container">
            <h1>Client Management</h1>
            <form action="ClientServlet" method="post">
                <input type="hidden" name="clientID" value="${client.clientID}">
                
                <div class="form-group">
                    <label for="clientName">Client Name:</label>
                    <input type="text" id="clientName" name="clientName" value="${client.clientName}" required>
                    
                    <label for="contactPerson">Contact Person:</label>
                    <input type="text" id="contactPerson" name="contactPerson" value="${client.contactPerson}">
                </div>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${client.email}" required>

                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" value="${client.phoneNumber}" required>

                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${client.address}" required>

                <label for="crm_id">CRM ID:</label>
                <input type="text" id="crm_id" name="crm_id" value="${client.crm_id}">

                <label for="service_history">Service History:</label>
                <input type="text" id="service_history" name="service_history" value="${client.service_history}">

                <label for="businessType">Business Type:</label>
                <input type="text" id="businessType" name="businessType" value="${client.businessType}">

                <label for="preferredContactMethod">Preferred Contact Method:</label>
                <input type="text" id="preferredContactMethod" name="preferredContactMethod" value="${client.preferredContactMethod}">

                <label for="preferredServiceTimes">Preferred Service Times:</label>
                <input type="text" id="preferredServiceTimes" name="preferredServiceTimes" value="${client.preferredServiceTimes}">

                <label for="accountManager">Account Manager:</label>
                <input type="text" id="accountManager" name="accountManager" value="${client.accountManager}">

                <label for="isVIP">VIP Client:</label>
                <input type="checkbox" id="isVIP" name="isVIP" ${client.isVIP ? "checked" : ""}>

                <button type="submit">Add Client</button>
            </form>
        </div>

        <!-- Client List Container -->
        <div class="client-list-container">
            <h2>Client List</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Contact Person</th>
                        <th>Email</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="client" items="${clientList}">
                        <tr>
                            <td>${client.clientID}</td>
                            <td>${client.clientName}</td>
                            <td>${client.contactPerson}</td>
                            <td>${client.email}</td>
                            <td>${client.phoneNumber}</td>
                            <td>${client.address}</td>
                            <td>
                                <a href="ClientServlet?action=edit&clientID=${client.clientID}">Edit</a> |
                                <a href="ClientServlet?action=delete&clientID=${client.clientID}" onclick="return confirm('Are you sure you want to delete this client?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </body>
</html>
