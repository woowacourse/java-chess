package chess.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPDataSource {
    private static BasicDataSource basicDataSource = new BasicDataSource();

    private static final String PROPERTIES_FILE_NAME = "jdbc.properties";
    private static final String PROPERTY_DRIVER = "jdbc.driver";
    private static final String PROPERTY_URL = "jdbc.url";
    private static final String PROPERTY_USERNAME = "jdbc.username";
    private static final String PROPERTY_PASSWORD = "jdbc.password";
    private static final int MIN_IDLE = 5;
    private static final int MAX_IDLE = 10;
    private static final int MAX_OPEN_PREPARED_STATEMENTS = 100;

    static {
        Properties properties = loadProperties();

        basicDataSource.setDriverClassName(properties.getProperty(PROPERTY_DRIVER));
        basicDataSource.setUrl(properties.getProperty(PROPERTY_URL));
        basicDataSource.setUsername(properties.getProperty(PROPERTY_USERNAME));
        basicDataSource.setPassword(properties.getProperty(PROPERTY_PASSWORD));
        basicDataSource.setMinIdle(MIN_IDLE);
        basicDataSource.setMaxIdle(MAX_IDLE);
        basicDataSource.setMaxOpenPreparedStatements(MAX_OPEN_PREPARED_STATEMENTS);
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        ClassLoader classLoader = DBCPDataSource.class.getClassLoader();

        try (InputStream fileInputStream = classLoader.getResourceAsStream(PROPERTIES_FILE_NAME)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
