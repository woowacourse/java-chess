package chess.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSource {
    private String url;
    private Properties properties;
    private String driverClassName;

    public static DataSource getInstance() {
        return LazyHolder.INSTANCE;
    }

    private DataSource(String path) {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
            properties = new Properties();
            properties.load(in);
            url = properties.getProperty("url");
            driverClassName = properties.getProperty("driver-class-name");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    private static class LazyHolder {
        private static final DataSource INSTANCE = new DataSource("jdbc.properties");
    }
}
