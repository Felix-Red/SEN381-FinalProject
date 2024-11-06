/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package BusinessLogic;


import DataLayer.DBConnection;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import java.util.Properties;
import java.sql.ResultSet;

/**
 *
 * @author user-pc
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
    private void sendConfirmationEmail(String name, String email, String startDate, String serviceType) {
        try {
//            LOGGER.info("Sending confirmation email to: " + email);
            
            Properties properties = new Properties();
            //properties.load();
            
            String sendGridApiKey = "";
            Email from = new Email("577502@student.belgiumcampus.ac.za");
            String subject = "Welcome to ApexCare Solutions!";
            Email to = new Email(email);
            Content content = new Content("text/plain", "Dear " + name + ",\n\n" +
                    "Thank you for registering with ApexCare Solutions. Your subscription for the " + serviceType + 
                    " service starts on " + startDate + ".\n\n" +
                    "If you have any questions, feel free to contact us.\n\n" +
                    "Best regards,\nApexCare Solutions");

            Mail mail = new Mail(from, subject, to, content);
            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            sg.api(request);
//            LOGGER.info("Email sent successfully.");
        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "Error while sending email", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String company = request.getParameter("company");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String serviceType = request.getParameter("serviceType");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String priority = request.getParameter("priority");
        String comments = request.getParameter("comments");
        String username = request.getParameter("username");
        String password = request.getParameter("password"); 

        // Connect to the database and insert the new user
    LocalDate startLocalDate = LocalDate.parse(startDate);
    LocalDate endLocalDate = LocalDate.parse(endDate);
    Date sqlStartDate = Date.valueOf(startLocalDate);
    Date sqlEndDate = Date.valueOf(endLocalDate);
    LocalDate renewalLocalDate = startLocalDate.plusYears(1);
    Date sqlRenewalDate = Date.valueOf(renewalLocalDate);

    // Connect to the database and insert the new user
    try (Connection conn = DBConnection.getConnection()) { 
        String sql = "INSERT INTO public.clients (name, company, email, phone, address, service_type, " +
                     "contract_start_date, contract_end_date, priority_level, comments, username, password) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int clientId = 0;
        try (PreparedStatement clientStmt = conn.prepareStatement(sql)) {
            clientStmt.setString(1, name);
            clientStmt.setString(2, company);
            clientStmt.setString(3, email);
            clientStmt.setString(4, phone);
            clientStmt.setString(5, address);
            clientStmt.setString(6, serviceType);
            clientStmt.setDate(7, sqlStartDate);
            clientStmt.setDate(8, sqlEndDate);
            clientStmt.setString(9, priority);
            clientStmt.setString(10, comments);
            clientStmt.setString(11, username);
            clientStmt.setString(12, password);

            ResultSet rs = clientStmt.executeQuery();
            if (rs.next()) {
                clientId = rs.getInt("client_id");  // Get the generated client ID
            }
        }
        if (clientId > 0) {  // Ensure client ID was retrieved
            String contractSql = "INSERT INTO public.contract (client_id, contract_terms, start_date, end_date, renewal_date) " +
                         "VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement contractStmt = conn.prepareStatement(contractSql)) {
                    contractStmt.setInt(1, clientId);
                    contractStmt.setString(2, "Standard Terms");  // Adding 'contract_terms'
                    contractStmt.setDate(3, sqlStartDate);
                    contractStmt.setDate(4, sqlEndDate);
                    contractStmt.setDate(5, sqlRenewalDate);  // Adding renewal date

                    contractStmt.executeUpdate();
                }
        }

            sendConfirmationEmail(name, email, startDate, serviceType);
        
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exceptions, possibly redirect to an error page
    }

    // Redirect to the login page after successful registration
    response.sendRedirect("clientLogin.jsp");
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
