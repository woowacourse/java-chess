package chess.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnector {

    private static final String URL_FORMAT = "jdbc:mysql://%s/%s?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String SERVER = "localhost";
    private static final String DATABASE = "chess";
    private static final String USER_NAME = "whale";
    private static final String PASSWORD = "whale";

    private JdbcConnector() {
        throw new AssertionError();
    }

    public static Connection getConnection() {
        searchDriver();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(String.format(URL_FORMAT, SERVER, DATABASE), USER_NAME, PASSWORD);
        } catch (Exception e) {
            System.err.println("!! Connection error : " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    private static void searchDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("!! JDBC DRIVER load error : " + e.getMessage());
            e.printStackTrace();
        }
    }


}
