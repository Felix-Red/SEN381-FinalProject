<%@ page import="java.util.List" %>
<%@ page import="DataLayer.FeedbackEntry" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Feedback</title>
    <link rel="stylesheet" href="Styling/feedback.css">
    <link rel="stylesheet" href="Styling/adminDashboard.css">
</head>
<body>
        <div class="header">
            <h1>Customer Feedback</h1>
            <p>View feedback provided by our customers.</p>
        </div>

        <!-- Sorting Buttons -->
        <div class="sorting">
            <a href="FeedbackServlet?sortOrder=ASC" class="sort-button">Sort by Satisfaction (Ascending)</a>
            <a href="FeedbackServlet?sortOrder=DESC" class="sort-button">Sort by Satisfaction (Descending)</a>
        </div>

        <!-- Feedback Table -->
        <div class="feedback-table">
            <table border="1" cellpadding="10" cellspacing="0">
                <thead>
                    <tr>
                        <th>Customer Name</th>
                        <th>Satisfaction Rating</th>
                        <th>Comments</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<FeedbackEntry> feedbackList = (List<FeedbackEntry>) request.getAttribute("feedbackList");
                        if (feedbackList != null && !feedbackList.isEmpty()) {
                            for (FeedbackEntry feedback : feedbackList) {
                    %>
                    <tr>
                        <td><%= feedback.getCustomerName() %></td>
                        <td><%= feedback.getSatisfactionRating() %></td>
                        <td><%= feedback.getComments() %></td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="3">No feedback available.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
</body>
</html>


