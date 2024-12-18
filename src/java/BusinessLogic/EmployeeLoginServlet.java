/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package BusinessLogic;

import DataLayer.DBConnection;
import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user-pc
 */
@WebServlet(name = "EmployeeLoginServlet", urlPatterns = {"/EmployeeLoginServlet"})
public class EmployeeLoginServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmployeeLoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeLoginServlet at " + request.getContextPath() + "</h1>");
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
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            // Check if the Employees table exists
            String checkTableQuery = "SELECT 1 FROM employees LIMIT 1";
            try (PreparedStatement checkTableStmt = conn.prepareStatement(checkTableQuery)) {
                checkTableStmt.executeQuery();
            } catch (SQLException tableNotFound) {
                // Table does not exist
                request.setAttribute("errorMessage", "The Employees table does not exist.");
                request.getRequestDispatcher("employeeLogin.jsp").forward(request, response);
                return;
            }

            // Table exists, proceed to authenticate the user
            String query = "SELECT * FROM Employees WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Employee exists and password matches
                    HttpSession session = request.getSession();
                    session.setAttribute("employeeUsername", username);
                    session.setAttribute("successMessage", "Logged in successfully.");
                    response.sendRedirect("DashboardServlet");
                } else {
                    // Invalid credentials
                    request.setAttribute("errorMessage", "Invalid username or password.");
                    request.getRequestDispatcher("employeeLogin.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Employee login servlet that checks table existence and verifies credentials.";
    }// </editor-fold>
}
