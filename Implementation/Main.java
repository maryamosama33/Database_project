import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String username = "admin30";
        String password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, username, password);
            System.out.println("Connected to the database successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close the database connection: " + e.getMessage());
                }
            }
        }
    }
}