package chess.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME,
                    PASSWORD);
        } catch (final SQLException exception) {
            System.err.println("DB 연결 오류:" + exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
