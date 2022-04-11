package chess.db;

import chess.util.SqlConnectionException;

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
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SqlConnectionException("Connection 연결에 실패했습니다.", e);
        }
    }
}
