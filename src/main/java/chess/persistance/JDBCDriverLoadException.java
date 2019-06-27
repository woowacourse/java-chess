package chess.persistance;

public class JDBCDriverLoadException extends RuntimeException {
    public JDBCDriverLoadException(final String message) {
        super(message);
    }
}
