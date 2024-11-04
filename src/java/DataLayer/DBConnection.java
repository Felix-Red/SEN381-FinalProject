package DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    public static Connection getConnection() {
        Connection connection = null;
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("dbconfig.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find dbconfig.properties. Make sure it is in the classpath.");
            }
            
            // Load the properties from the file
            Properties properties = new Properties();
            properties.load(input);

            // Get the properties from the file
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            // Load the PostgreSQL JDBC driver and establish a connection
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace(); // Print any errors to help debug
        }

        return connection;
    }
}
