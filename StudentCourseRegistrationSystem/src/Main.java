import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Student Course Registration System");
        System.out.println("Enter Student ID:");

        // Change nextInt() to nextLine() to read Student ID as a String
        String studentId = scanner.nextLine();  // Read input as a String

        System.out.println("Enter Course ID you want to register for:");
        int courseId = scanner.nextInt();  // You can keep this as nextInt() if course ID is numeric

        // Call the registration function
        StudentRegistration.registerStudentForCourse(Integer.parseInt(studentId), courseId);
    }
}