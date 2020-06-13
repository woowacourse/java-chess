package chess.dao.jdbc;

import chess.dao.error.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcChessDataSource implements DataSource {
    @Override
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DatabaseException(DatabaseException.DEPENDENCY_NOT_FOUND_MESSAGE);
        }

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/java-chess", "root", "p-vibe");
        } catch (SQLException e) {
            throw new DatabaseException(DatabaseException.CONNECTION_FAILED_MESSAGE);
        }
    }
}
