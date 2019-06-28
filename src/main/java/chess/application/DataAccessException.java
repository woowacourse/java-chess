package chess.application;

public class DataAccessException extends RuntimeException {
    public DataAccessException() {
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(Exception e) {
        super(e);
    }
}
