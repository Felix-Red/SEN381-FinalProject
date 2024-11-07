package BusinessLogic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

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
        int requestApprovedCount = 0;

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

            // Get new request count (requests that have not been assigned)
            String sqlRequests = "SELECT COUNT(*) AS count FROM requests WHERE technician_id IS NULL";
            PreparedStatement stmtRequests = conn.prepareStatement(sqlRequests);
            ResultSet rsRequests = stmtRequests.executeQuery();

            if (rsRequests.next()) {
                newRequestCount = rsRequests.getInt("count");
            }

            // Get approved request count (requests that have a technician assigned)
            String sqlApprovedRequests = "SELECT COUNT(*) AS count FROM requests WHERE technician_id IS NOT NULL";
            PreparedStatement stmtApprovedRequests = conn.prepareStatement(sqlApprovedRequests);
            ResultSet rsApprovedRequests = stmtApprovedRequests.executeQuery();

            if (rsApprovedRequests.next()) {
                requestApprovedCount = rsApprovedRequests.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the counts in the request attributes
        request.setAttribute("totalTechnicianCount", technicianCount);
        request.setAttribute("totalCustomerCount", customerCount);
        request.setAttribute("newRequestCount", newRequestCount);
        request.setAttribute("requestApprovedCount", requestApprovedCount);

        // Forward to the dashboard JSP page
        request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
    }
}
