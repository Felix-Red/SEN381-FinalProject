/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package BusinessLogic;

import DataLayer.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

/**
 *
 * @author user-pc
 */
@WebServlet(name = "ServiceRequestServlet", urlPatterns = {"/ServiceRequestServlet"})
public class ServiceRequestServlet extends HttpServlet {

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
            out.println("<title>Servlet ServiceRequestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServiceRequestServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String serviceType = request.getParameter("serviceType"); // Capture the selected service type
        String description = request.getParameter("description");

        // Retrieve the client ID from the session
        HttpSession session = request.getSession();
        Integer clientId = (Integer) session.getAttribute("clientId");

        if (clientId == null) {
            // If the client is not logged in, redirect to the login page
            response.sendRedirect("clientLogin.jsp");
            return;
        }

        // Insert the service request into the database with technician_id as NULL initially
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO requests (client_id, technician_id, service_type, description, request_date, status) " +
                           "VALUES (?, NULL, ?, ?, CURRENT_DATE, 'Pending')";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, clientId);  // Set the client_id
                stmt.setString(2, serviceType);  // Set the service type (Standard or Premium)
                stmt.setString(3, description);  // Set the description

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Successfully added the service request
                    response.sendRedirect("serviceRequestSuccess.jsp");
                } else {
                    // If the insert failed, show an error message
                    request.setAttribute("errorMessage", "Failed to submit your request.");
                    request.getRequestDispatcher("serviceRequests.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error occurred.");
            request.getRequestDispatcher("serviceRequests.jsp").forward(request, response);
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
