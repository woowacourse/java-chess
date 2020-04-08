package chess.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JdbcProperties {
    private String url;
    private String userName;
    private String password;

    public JdbcProperties() {
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

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
