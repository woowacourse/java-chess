package chess.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/chess";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static void loadDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Connection getConnection(){
        loadDriver();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return  connection;
    }
}
