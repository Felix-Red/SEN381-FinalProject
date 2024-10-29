package BusinessLogic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import DataLayer.DBConnection;

@WebServlet("/test-connection")
public class TestConnectionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            Connection connection = DBConnection.getConnection();
            if (connection != null && !connection.isClosed()) {
                response.getWriter().println("<h3>Database connection successful!</h3>");
            } else {
                response.getWriter().println("<h3>Failed to connect to the database.</h3>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Exception occurred: " + e.getMessage() + "</h3>");
        }
    }
}
