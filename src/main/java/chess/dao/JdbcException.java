package chess.dao;

public class JdbcException extends RuntimeException {
    public JdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
