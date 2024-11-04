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

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT COUNT(*) AS count FROM tbltechnicians";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                technicianCount = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the technician count in the request attribute
        request.setAttribute("totalTechnicianCount", technicianCount);

        // Forward to the dashboard JSP page
        request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
    }
}
