package BusinessLogic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import DataLayer.DBConnection;
import DataLayer.Technicians;

@WebServlet("/TechniciansServlet")
public class TechnicianServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Technicians> technicianList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Database connection established");
            } else {
                System.out.println("Failed to establish database connection");
            }

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT technicianid, technicianname, techniciancity, technicianmobile, technicianemail FROM tbltechnicians");

            while (rs.next()) {
                Technicians technician = new Technicians(
                    rs.getInt("technicianid"),
                    rs.getString("technicianname"),
                    rs.getString("techniciancity"),
                    rs.getString("technicianmobile"),
                    rs.getString("technicianemail")
                );
                technicianList.add(technician);
                System.out.println("Fetched Technician: " + technician.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (technicianList.isEmpty()) {
            System.out.println("No technicians found in the database");
        } else {
            System.out.println("Technicians found: " + technicianList.size());
        }

        request.setAttribute("technicianList", technicianList);
        request.getRequestDispatcher("Technicians.jsp").forward(request, response);
    }
}
