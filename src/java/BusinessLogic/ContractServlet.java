package BusinessLogic;

import DataLayer.Contract;
import DataLayer.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 * ContractServlet handles contract management for clients, including fetching,
 * creating, and deleting contracts.
 */
@WebServlet(name = "ContractServlet", urlPatterns = {"/ContractServlet"})
public class ContractServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ContractServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContractServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer clientId = (Integer) session.getAttribute("clientId");

        // Fetch active contracts for the logged-in client
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM contract WHERE client_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clientId);

            ResultSet rs = stmt.executeQuery();
            List<Contract> contracts = new ArrayList<>();

            while (rs.next()) {
                Contract contract = new Contract(
                    rs.getInt("contract_id"),
                    rs.getInt("client_id"),
                    rs.getString("contract_terms"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getDate("renewal_date")
                );
                contracts.add(contract);
            }

            // Set the contracts list as a request attribute
            request.setAttribute("contracts", contracts);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Forward to contracts.jsp
        request.getRequestDispatcher("contracts.jsp").forward(request, response);
    }
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Determine if the request is to delete or add a contract
    String methodType = request.getParameter("_method");
    if (methodType != null && methodType.equals("delete")) {
        // Handle deletion of a contract
        int contractId = Integer.parseInt(request.getParameter("contractId"));

        try (Connection conn = DBConnection.getConnection()) {
            // SQL query to delete a contract
            String sql = "DELETE FROM public.contract WHERE contract_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, contractId);
                stmt.executeUpdate();
            }
            response.sendRedirect("deleteSuccess.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error deleting contract.");
            request.getRequestDispatcher("contracts.jsp").forward(request, response);
        }
    } else {
        // Handle adding a new contract

        // Retrieve the clientId from the request or the session
        String clientIdParam = request.getParameter("clientId");
        HttpSession session = request.getSession();
        Integer clientId = null;

        // Attempt to retrieve clientId from request
        if (clientIdParam != null && !clientIdParam.isEmpty()) {
            try {
                clientId = Integer.parseInt(clientIdParam);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid client ID.");
                request.getRequestDispatcher("contracts.jsp").forward(request, response);
                return;
            }
        }

        // If clientId is still null, try to get it from session
        if (clientId == null) {
            clientId = (Integer) session.getAttribute("clientId");
            if (clientId == null) {
                request.setAttribute("errorMessage", "Client ID not found. Please log in.");
                request.getRequestDispatcher("contracts.jsp").forward(request, response);
                return;
            }
        }

        // Retrieve other form data
        String contractTerms = request.getParameter("contractTerms");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        if (contractTerms == null || startDate == null || endDate == null) {
            request.setAttribute("errorMessage", "All form fields are required.");
            request.getRequestDispatcher("contracts.jsp").forward(request, response);
            return;
        }

        // Calculate renewal date based on the end date
        java.sql.Date renewalDate = calculateRenewalDate(java.sql.Date.valueOf(endDate));

        try (Connection conn = DBConnection.getConnection()) {
            // SQL query to insert a new contract
            String sql = "INSERT INTO public.contract (client_id, contract_terms, start_date, end_date, renewal_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, clientId);
                stmt.setString(2, contractTerms);
                stmt.setDate(3, java.sql.Date.valueOf(startDate));
                stmt.setDate(4, java.sql.Date.valueOf(endDate));
                stmt.setDate(5, renewalDate);
                stmt.executeUpdate();
            }

            response.sendRedirect("successPage.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during contract creation. Please try again.");
            request.getRequestDispatcher("contracts.jsp").forward(request, response);
        }
    }
}


    private java.sql.Date calculateRenewalDate(java.sql.Date endDate) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(java.util.Calendar.DAY_OF_MONTH, 10);
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve contract_id from the request to delete a specific contract
        int contractId = Integer.parseInt(request.getParameter("contractId"));

        try (Connection conn = DBConnection.getConnection()) {
            // SQL query to delete a contract
            String sql = "DELETE FROM public.contract WHERE contract_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, contractId);
                stmt.executeUpdate();
            }
            response.sendRedirect("contracts.jsp?message=Contract deleted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("contracts.jsp?error=Error deleting contract");
        }
    }

    @Override
    public String getServletInfo() {
        return "Contract Servlet for handling contract management.";
    }
}
