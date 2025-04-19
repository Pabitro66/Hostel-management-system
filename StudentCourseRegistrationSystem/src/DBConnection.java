import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Use logging framework (e.g., SLF4J with Logback) for production code
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DBConnection.class.getName());

    public static Connection connect() {
        try {
            // Connect to MySQL database
            String url = "jdbc:mysql://localhost:3306/student_course_db";
            String user = "disco_potato"; // Your MySQL username
            String password = "disco_potato"; // Your MySQL password

            // Return the connection directly without storing in a variable
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // Replace printStackTrace() with proper logging
            logger.severe("Database connection failed: " + e.getMessage());
            return null; // Return null if connection fails
        }
    }
}
