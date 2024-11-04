package BusinessLogic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import DataLayer.DBConnection;

@WebServlet("/DeleteTechnicianServlet")
public class DeleteTechnicianServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String technicianId = request.getParameter("id");

        if (technicianId != null) {
            try (Connection conn = DBConnection.getConnection()) {
                String sql = "DELETE FROM tbltechnicians WHERE technicianid = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(technicianId));

                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Technician deleted successfully!");
                } else {
                    System.out.println("Failed to delete technician.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Redirect back to the Technicians list page
        response.sendRedirect("TechniciansServlet");
    }
}
