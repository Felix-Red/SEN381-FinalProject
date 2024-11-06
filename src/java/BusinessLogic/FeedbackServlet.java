package BusinessLogic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DataLayer.DBConnection;
import DataLayer.FeedbackEntry;

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FeedbackEntry> feedbackList = new ArrayList<>();
        String sortOrder = request.getParameter("sortOrder");

        if (sortOrder == null || (!sortOrder.equalsIgnoreCase("ASC") && !sortOrder.equalsIgnoreCase("DESC"))) {
            sortOrder = "ASC"; // Default sorting order
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT f.satisfaction_rating, f.comments, c.name " +
                         "FROM feedback f " +
                         "JOIN clients c ON f.client_id = c.client_id " +
                         "ORDER BY f.satisfaction_rating " + sortOrder;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String satisfactionRating = rs.getString("satisfaction_rating");
                String comments = rs.getString("comments");
                String customerName = rs.getString("name");

                feedbackList.add(new FeedbackEntry(satisfactionRating, comments, customerName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("feedbackList", feedbackList);
        request.setAttribute("sortOrder", sortOrder);
        request.getRequestDispatcher("Feedback.jsp").forward(request, response);
    }
}

