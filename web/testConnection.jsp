<%@ page import="java.sql.Connection" %>
<%@ page import="java.io.PrintWriter" %>
<%
    Connection connection = null;
    try {
        connection = DataLayer.DBConnection.getConnection();
        if (connection != null && !connection.isClosed()) {
            out.println("<h3>Database connection successful!</h3>");
        } else {
            out.println("<h3>Failed to connect to the database.</h3>");
        }
    } catch (Exception e) {
        e.printStackTrace(out);
        out.println("<h3>Exception occurred: " + e.getMessage() + "</h3>");
    } finally {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
%>
