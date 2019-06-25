package chess.persistance;

public class JDBCConnectException extends RuntimeException {
    public JDBCConnectException(final String message) {
        super(message);
    }
}
