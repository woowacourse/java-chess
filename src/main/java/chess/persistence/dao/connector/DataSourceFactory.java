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

    private static DataSourceFactory instance;
    private static MysqlDataSource ds;

    public static DataSourceFactory getInstance(){
        return instance == null ? instance = new DataSourceFactory() : instance;
    }

    @Override
    public DataSource create() {
        try {
            Properties properties = loadProperty();
            if(ds == null) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Connection getConnection(){
        try {
            return create().getConnection();
        } catch (SQLException e) {
            System.out.println("여기오륲");
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private Properties loadProperty() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

}
