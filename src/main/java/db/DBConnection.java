package db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DBConnection {
    private static Connection connection = null;

    static {
        Properties properties = new Properties();
        try {
            Reader reader = new FileReader("src/main/resources/db.properties");
            properties.load(reader);
            String server = properties.getProperty("server");
            String database = properties.getProperty("database");
            String option = properties.getProperty("option");
            String userName = properties.getProperty("userName");
            String password = properties.getProperty("password");

            loadDrive();
            connectDrive(server, database, option, userName, password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadDrive() {
        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void connectDrive(String server, String database, String option, String userName, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 드라이버 연결해제
    public static void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println("connection 오류:" + e.getMessage());
        }
    }

    public static Connection getInstance() {
        if (Objects.isNull(connection)) {
            throw new DataBaseConnectionException();
        }
        return connection;
    }
}
