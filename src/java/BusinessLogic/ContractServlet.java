/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import java.util.logging.Level;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.HashSet;


/**
 *
 * @author user-pc
 */
@WebServlet(name = "ContractServlet", urlPatterns = {"/ContractServlet"})
public class ContractServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer clientId = (Integer) session.getAttribute("clientId");

//        if (clientId == null) {
//            // If client is not logged in, redirect to login page
//            response.sendRedirect("clientLogin.jsp");
//            return;
//        }

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
    // Handle exceptions as necessary
}

// Forward to contracts.jsp
    request.getRequestDispatcher("contracts.jsp").forward(request, response);
    }
    
    private List<Contract> getActiveContractsByClient(int clientId) {
        List<Contract> contracts = new ArrayList<>();
        String query = "SELECT * FROM contract WHERE client_id = ?";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clientId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Contract contract = new Contract();
                contract.setContractId(rs.getInt("contract_id"));
                contract.setContractTerms(rs.getString("contract_terms"));
                contract.setStartDate(rs.getDate("start_date"));
                contract.setEndDate(rs.getDate("end_date"));
                contract.setRenewalDate(rs.getDate("renewal_date"));
                
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clientId = Integer.parseInt(request.getParameter("clientId"));
        String contractTerms = request.getParameter("contractTerms");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
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
            response.sendRedirect("contracts.jsp?error=Error adding contract");
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
