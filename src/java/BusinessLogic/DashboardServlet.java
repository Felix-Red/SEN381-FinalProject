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
import DataLayer.DBConnection;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int technicianCount = 0;
        int customerCount = 0;
        int newRequestCount = 0;

        try (Connection conn = DBConnection.getConnection()) {
            // Get technician count
            String sqlTechnicians = "SELECT COUNT(*) AS count FROM tbltechnicians";
            PreparedStatement stmtTechnicians = conn.prepareStatement(sqlTechnicians);
            ResultSet rsTechnicians = stmtTechnicians.executeQuery();

            if (rsTechnicians.next()) {
                technicianCount = rsTechnicians.getInt("count");
            }

            // Get customer count
            String sqlCustomers = "SELECT COUNT(*) AS count FROM clients";
            PreparedStatement stmtCustomers = conn.prepareStatement(sqlCustomers);
            ResultSet rsCustomers = stmtCustomers.executeQuery();

            if (rsCustomers.next()) {
                customerCount = rsCustomers.getInt("count");
            }

            // Get new request count
            String sqlRequests = "SELECT COUNT(*) AS count FROM requests WHERE technician_id IS NULL";
            PreparedStatement stmtRequests = conn.prepareStatement(sqlRequests);
            ResultSet rsRequests = stmtRequests.executeQuery();

            if (rsRequests.next()) {
                newRequestCount = rsRequests.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the technician, customer, and new request counts in the request attributes
        request.setAttribute("totalTechnicianCount", technicianCount);
        request.setAttribute("totalCustomerCount", customerCount);
        request.setAttribute("newRequestCount", newRequestCount);

        // Forward to the dashboard JSP page
        request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
    }
}
