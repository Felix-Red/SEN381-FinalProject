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
import DataLayer.Technicians;

@WebServlet("/EditTechnicianServlet")
public class EditTechnicianServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String technicianId = request.getParameter("id");

        if (technicianId != null) {
            try (Connection conn = DBConnection.getConnection()) {
                String query = "SELECT technicianid, technicianname, techniciancity, technicianmobile, technicianemail FROM tbltechnicians WHERE technicianid = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, Integer.parseInt(technicianId));
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    Technicians technician = new Technicians(
                        rs.getInt("technicianid"),
                        rs.getString("technicianname"),
                        rs.getString("techniciancity"),
                        rs.getString("technicianmobile"),
                        rs.getString("technicianemail")
                    );
                    request.setAttribute("technician", technician);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("addTechnician.jsp").forward(request, response);
    }
}
