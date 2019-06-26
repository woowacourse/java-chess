package chess.persistance.exceptions;

public class JDBCDriverLoadException extends RuntimeException {
    public JDBCDriverLoadException(final String message) {
        super(message);
    }
}
