package chess.persistance.exceptions;

public class DataAccessException extends RuntimeException {
    public DataAccessException(final Throwable cause) {
        super(cause);
    }
}
