import java.sql.*;

public class DatabaseExample {
    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");

            // Create a table
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS employees (id INT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255))");

            // Add employee
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO employees VALUES (?, ?, ?)");
            pstmt.setInt(1, 1);
            pstmt.setString(2, "John Doe");
            pstmt.setString(3, "john@example.com");
            pstmt.executeUpdate();

            // View employees
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Email: " + rs.getString("email"));
            }

            // Update employee
            pstmt = conn.prepareStatement("UPDATE employees SET name = ? WHERE id = ?");
            pstmt.setString(1, "Jane Doe");
            pstmt.setInt(2, 1);
            pstmt.executeUpdate();

            // Delete employee
            pstmt = conn.prepareStatement("DELETE FROM employees WHERE id = ?");
            pstmt.setInt(1, 1);
            pstmt.executeUpdate();

            // Close resources
            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

