package chess.exception;

public class SQLQueryException extends RuntimeException {

    public SQLQueryException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
