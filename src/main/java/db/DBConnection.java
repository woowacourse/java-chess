package db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final Properties properties = new Properties();
    private static String server;
    private static String database;
    private static String option;
    private static String userName;
    private static String password;

    static {
        try {
            Reader reader = new FileReader("src/main/resources/db.properties");
            properties.load(reader);

            server = properties.getProperty("server");
            database = properties.getProperty("database");
            option = properties.getProperty("option");
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        loadDrive();
        return connectDrive(server, database, option, userName, password);
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println("con close 실패 :" + e.getMessage());
        }
    }

    private static void loadDrive() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Connection connectDrive(String server, String database, String option, String userName, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
            return connection;
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
            throw new DataBaseConnectionException();
        }
    }
}