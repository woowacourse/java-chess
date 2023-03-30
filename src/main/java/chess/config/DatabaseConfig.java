package chess.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    public Connection getConnection() throws IOException {
        String propFile = "src/main/resources/config.properties";
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(propFile);
        properties.load(new java.io.BufferedInputStream(fis));

        String server = properties.getProperty("server");
        String database = properties.getProperty("database");
        String option = properties.getProperty("option");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        try {
            return DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?" + option, username,
                    password);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
