package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCRepo {
    private static final String dbhost = "jdbc:mysql://localhost:3306/hausaufgabe5?useSSL=false";
    private static final String username = "root";
    private static final String password = "ABcd1234!";
    private static Connection conn;

    @SuppressWarnings("finally")
    public static Connection createDBconnection() {
        try  {
            conn = DriverManager.getConnection(
                    dbhost, username, password);
        } catch (SQLException e) {
            System.out.println("Cannot create database connection");
            e.printStackTrace();
        } finally {
            return conn;
        }
    }
}