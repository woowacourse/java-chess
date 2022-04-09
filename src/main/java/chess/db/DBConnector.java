package chess.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class DBConnector {

    private Connection connection;

    public DBConnector() {
        this.connection = JdbcConnector.getConnection();
    }

    public Connection getConnection() {
        try {
            if (Objects.isNull(connection) || connection.isClosed()) {
                connection = JdbcConnector.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Connection 연결에 실패했습니다.");
        }
        return connection;
    }
}
