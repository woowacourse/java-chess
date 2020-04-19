package chess.dao;

public class CustomSQLException extends RuntimeException {
    public CustomSQLException(String message, Throwable cause) {
        super(message, cause);
    }
}
