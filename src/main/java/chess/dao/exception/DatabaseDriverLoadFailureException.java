package chess.dao.exception;

public class DatabaseDriverLoadFailureException extends RuntimeException {

    public DatabaseDriverLoadFailureException(final String message) {
        super(message);
    }
}
