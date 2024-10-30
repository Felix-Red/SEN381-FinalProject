package BusinessLogic;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DataLayer.DBConnection;
import DataLayer.Client;


@WebServlet("/clientManagementServlet")
public class clientManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Process GET request to display clients
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = getClients();
        request.setAttribute("clientList", clients);
        request.getRequestDispatcher("clientManagement.jsp").forward(request, response);
    }

    // Process POST request to add a new client
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String clientName = request.getParameter("clientName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String crmId = request.getParameter("crm_id");
        String serviceHistory = request.getParameter("service_history");
        String businessType = request.getParameter("businessType");
        String preferredContactMethod = request.getParameter("preferredContactMethod");
        String contractStartDate = request.getParameter("contract_start_date");
        String contractEndDate = request.getParameter("contract_end_date");
        String contractType = request.getParameter("contract_type");
        String contractValue = request.getParameter("contract_value");
        String contractStatus = request.getParameter("contract_status");

        // Insert new client into the database
        addClient(clientName, email, phoneNumber, address, crmId, serviceHistory, businessType, preferredContactMethod, contractStartDate, contractEndDate, contractType, contractValue, contractStatus);

        // Redirect to GET method to display updated client list
        response.sendRedirect("clientManagementServlet");
    }

    // Method to add a new client to the database
    private void addClient(String clientName, String email, String phoneNumber, String address, String crmId, String serviceHistory, String businessType, String preferredContactMethod, String contractStartDate, String contractEndDate, String contractType, String contractValue, String contractStatus) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO tblClient (clientName, email, phoneNumber, address, crm_id, service_history, businessType, preferredContactMethod, contract_start_date, contract_end_date, contract_type, contract_value, contract_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, clientName);
            statement.setString(2, email);
            statement.setString(3, phoneNumber);
            statement.setString(4, address);
            statement.setString(5, crmId);
            statement.setString(6, serviceHistory);
            statement.setString(7, businessType);
            statement.setString(8, preferredContactMethod);
            statement.setDate(9, java.sql.Date.valueOf(contractStartDate));
            statement.setDate(10, java.sql.Date.valueOf(contractEndDate));
            statement.setString(11, contractType);
            statement.setBigDecimal(12, new java.math.BigDecimal(contractValue));
            statement.setString(13, contractStatus);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all clients from the database
    private List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM tblClient";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setClientID(rs.getInt("clientID"));
                client.setClientName(rs.getString("clientName"));
                client.setEmail(rs.getString("email"));
                client.setPhoneNumber(rs.getString("phoneNumber"));
                client.setAddress(rs.getString("address"));
                client.setCrmId(rs.getString("crm_id"));
                client.setServiceHistory(rs.getString("service_history"));
                client.setBusinessType(rs.getString("businessType"));
                client.setPreferredContactMethod(rs.getString("preferredContactMethod"));
                client.setContractStartDate(rs.getDate("contract_start_date"));
                client.setContractEndDate(rs.getDate("contract_end_date"));
                client.setContractType(rs.getString("contract_type"));
                client.setContractValue(rs.getBigDecimal("contract_value"));
                client.setContractStatus(rs.getString("contract_status"));
                clients.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }
}
