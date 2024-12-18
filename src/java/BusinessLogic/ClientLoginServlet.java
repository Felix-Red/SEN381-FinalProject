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
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user-pc
 */
@WebServlet(name = "ClientLoginServlet", urlPatterns = {"/ClientLoginServlet"})
public class ClientLoginServlet extends HttpServlet {

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
            out.println("<title>Servlet ClientLoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClientLoginServlet at " + request.getContextPath() + "</h1>");
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM public.clients WHERE email = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                int clientId = rs.getInt("client_id");
                String clientName = rs.getString("name");
                String clientCompany = rs.getString("company");
                String clientEmail = rs.getString("email");
                String clientPhone = rs.getString("phone");
                String clientAddress = rs.getString("address");
                String clientServiceType = rs.getString("service_type");
                String clientComments = rs.getString("comments");
                
                HttpSession session = request.getSession();
                session.setAttribute("clientId", clientId); 
                session.setAttribute("clientEmail", clientEmail);
                session.setAttribute("clientName", clientName);
                session.setAttribute("clientCompany", clientCompany);
                session.setAttribute("clientPhone", clientPhone);
                session.setAttribute("clientAddress", clientAddress);
                session.setAttribute("clientServiceType", clientServiceType);
                session.setAttribute("clientComments", clientComments);
                    
                    response.sendRedirect("clientDashboard.jsp");
                } else {
                    request.setAttribute("errorMessage", "Invalid email or password.");
                    request.getRequestDispatcher("clientLogin.jsp").forward(request, response);
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
        return "Short description";
    }// </editor-fold>

}
