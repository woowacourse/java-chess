package chess.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    private static Connection CONNECTION;

    public static Connection getConnection() {
        loadDriver();
        try {
            CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
            return CONNECTION;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return CONNECTION;
    }


    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
