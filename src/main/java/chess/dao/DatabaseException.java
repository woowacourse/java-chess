package chess.dao;

import java.sql.SQLException;

public class DatabaseException extends IllegalStateException {
    private static final String ERROR_PREFIX = "[DATABASE_ERROR] ";

    public DatabaseException(String message, SQLException e) {
        super(ERROR_PREFIX + message, e);
    }
}
