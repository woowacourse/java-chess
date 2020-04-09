package chess.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private final String url;
    private final String userName;
    private final String password;

    public DataSource() {
        Properties properties = readPropertiesFile();

        String server = properties.getProperty("server");
        String database = properties.getProperty("database");
        String option = properties.getProperty("option");

        this.url = "jdbc:mysql://" + server + "/" + database + option;
        this.userName = properties.getProperty("userName");
        this.password = properties.getProperty("password");
    }

    private Properties readPropertiesFile() {
        Properties properties = null;
        try {
            String file = "src\\jdbc.properties";
            FileInputStream fileInputStream = new FileInputStream(file);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("jdbc 속성 파일 오류 : " + e.getMessage());
            e.printStackTrace();
        }
        return properties;
    }

    public Connection getConnection() {
        loadDriver();
        return getConnectionFromDriver();
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver load 오류 : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Connection getConnectionFromDriver() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류 : " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("connection 오류 : " + e.getMessage());
        }
    }
}
