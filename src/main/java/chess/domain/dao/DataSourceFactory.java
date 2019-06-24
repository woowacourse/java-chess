package chess.domain.dao;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceFactory implements AbstractDataSourceFactory {
    private static final String URL_FORMAT = "jdbc:mysql://%s:%s/%s?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";

    @Override
    public DataSource create() {
        try {
            Properties properties = loadProperty();
            MysqlDataSource ds = new MysqlDataSource();
            ds.setUser(properties.getProperty("user"));
            ds.setPassword(properties.getProperty("password"));
            ds.setURL(properties.getProperty(
                    String.format(URL_FORMAT,
                            properties.getProperty("host"),
                            properties.getProperty("port"),
                            properties.getProperty("database"))
            ));
            return ds;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Properties loadProperty() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

}
