<%-- 
    Document   : overview
    Created on : 05 Nov 2024, 10:42:43
    Author     : user-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <% 
                //HttpSession session = request.getSession();
                String clientName = (String) session.getAttribute("clientName");
//                String 
                if (clientName == null) {
                    clientName = "Guest"; // Default name if not logged in
                }
            %>
        <h3>Overview</h3>
        <p>Welcome to your account overview. Here, you can see a summary of your recent activities and account information.</p><br><br>
        <div class="overview">
        <h3>Your Information</h3>
        <p><strong>Name:</strong> <%= session.getAttribute("clientName") %></p>
    <p><strong>Company:</strong> <%= session.getAttribute("clientCompany") %></p>
    <p><strong>Email:</strong> <%= session.getAttribute("clientEmail") %></p>
    <p><strong>Phone:</strong> <%= session.getAttribute("clientPhone") %></p>
    <p><strong>Address:</strong> <%= session.getAttribute("clientAddress") %></p><br><br>

        <h3>Service Information</h3>
        <p><strong>Service Type:</strong> <%= session.getAttribute("clientServiceType") %></p><br><br>


        <h4>Comments:</h4>
        <p><%= session.getAttribute("clientComments") %></p>
</div>
    </body>
</html>
