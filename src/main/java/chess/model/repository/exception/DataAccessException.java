package chess.model.repository.exception;

public class DataAccessException extends RuntimeException {

    public DataAccessException() {
    }

    public DataAccessException(String message) {
        super(message);
    }
}
