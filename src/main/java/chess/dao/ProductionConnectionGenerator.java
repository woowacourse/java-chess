package chess.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ProductionConnectionGenerator implements ConnectionGenerator {
    public Connection getConnection() {
        try {
            FileInputStream fis = new FileInputStream(
                    "C:\\Program Files\\intellijWorkspace\\intellijWorkspace\\java-chess\\src"
                            + "\\main\\resources\\connection-context.properties");
            Properties properties = new Properties();
            properties.load(fis);
            return DriverManager.getConnection(
                    "jdbc:mysql://" + properties.get("SERVER") + "/"
                            + properties.get("DATABASE") + properties.get("OPTION"),
                    properties.get("USERNAME") + "",
                    properties.get("PASSWORD") + ""
            );
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("파일이 존재하지 않습니다: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("입출력 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
