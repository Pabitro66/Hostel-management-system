import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class StudentRegistration {

    private static final Logger logger = Logger.getLogger(StudentRegistration.class.getName());

    public static void registerStudentForCourse(int studentId, int courseId) {
        // Using try-with-resources to ensure that the Connection, PreparedStatement, and ResultSet are closed automatically
        try (Connection conn = DBConnection.connect()) {
            if (conn == null) {
                logger.severe("Failed to connect to the database.");
                return;
            }

            // Check if the course is available
            String query = "SELECT current_enrollment, max_capacity FROM Courses WHERE course_id = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, courseId);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        int currentEnrollment = result.getInt("current_enrollment");
                        int maxCapacity = result.getInt("max_capacity");

                        if (currentEnrollment < maxCapacity) {
                            // Register student
                            String enrollQuery = "INSERT INTO Enrollments (student_id, course_id, status) VALUES (?, ?, 'registered')";
                            try (PreparedStatement enrollStatement = conn.prepareStatement(enrollQuery)) {
                                enrollStatement.setInt(1, studentId);
                                enrollStatement.setInt(2, courseId);
                                enrollStatement.executeUpdate();

                                // Update current enrollment
                                String updateQuery = "UPDATE Courses SET current_enrollment = current_enrollment + 1 WHERE course_id = ?";
                                try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
                                    updateStatement.setInt(1, courseId);
                                    updateStatement.executeUpdate();
                                    System.out.println("Successfully registered for the course.");
                                }
                            }
                        } else {
                            System.out.println("Course is full. Cannot register.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.severe("Error during database operation: " + e.getMessage());
        }
    }
}
