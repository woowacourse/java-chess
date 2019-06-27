package chess.persistence.dao.connector;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceFactory implements AbstractDataSourceFactory {
    private static final String URL_FORMAT = "jdbc:mysql://%s:%s/%s?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";

    private static MysqlDataSource ds;

    private DataSourceFactory() {
    }

    public static DataSourceFactory getInstance() {
        return DataSourceFactoryHandler.INSTANCE;
    }

    @Override
    public DataSource getDataSource() {
        try {
            Properties properties = loadProperty();
            if (ds == null) {
                ds = new MysqlDataSource();
            }
            ds.setUser(properties.getProperty("user"));
            ds.setPassword(properties.getProperty("password"));
            ds.setURL(
                    String.format(URL_FORMAT,
                            properties.getProperty("host"),
                            properties.getProperty("port"),
                            properties.getProperty("database"))
            );
            return ds;
        } catch (IOException e) {
            throw new IllegalArgumentException("IOException 발생 -> loadProperty() 에서 예외 발생");
        }
    }

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> getConnection 부분");
        }
    }

    private Properties loadProperty() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    private static class DataSourceFactoryHandler {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }
}
