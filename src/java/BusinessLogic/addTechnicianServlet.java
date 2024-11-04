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

@WebServlet("/addTechnicianServlet")
public class addTechnicianServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String technicianName = request.getParameter("technicianName");
        String techniciancity = request.getParameter("techniciancity");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO tbltechnicians (technicianname, techniciancity, technicianmobile, technicianemail) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, technicianName);
                pstmt.setString(2, techniciancity);
                pstmt.setString(3, mobile);
                pstmt.setString(4, email);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Technician added successfully!");
                } else {
                    System.out.println("Failed to add technician.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect back to the main page or a success page after form submission
        response.sendRedirect("TechniciansServlet");
    }
}