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

@WebServlet("/WorkOrderServlet")
public class WorkOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String[]> workOrders = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            // SQL query using INNER JOIN to get required fields, but only where technician_id is not null
            String sql = "SELECT req.request_id, req.description, cli.name, tech.technicianname, req.status " +
                         "FROM requests req " +
                         "INNER JOIN clients cli ON req.client_id = cli.client_id " +
                         "LEFT JOIN tbltechnicians tech ON req.technician_id = tech.technicianid " +
                         "WHERE req.technician_id IS NOT NULL";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String[] workOrder = new String[5];
                workOrder[0] = String.valueOf(rs.getInt("request_id"));
                workOrder[1] = rs.getString("description");
                workOrder[2] = rs.getString("name");
                workOrder[3] = rs.getString("technicianname") != null ? rs.getString("technicianname") : "Not Assigned";
                workOrder[4] = rs.getString("status") != null ? rs.getString("status") : "N/A";

                workOrders.add(workOrder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the work orders list as a request attribute
        request.setAttribute("workOrders", workOrders);

        // Forward the request to the JSP page
        request.getRequestDispatcher("workOrder.jsp").forward(request, response);
    }
}
