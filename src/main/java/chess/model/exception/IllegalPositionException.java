package chess.model.exception;

public class IllegalPositionException extends RuntimeException {
    public IllegalPositionException(final String message) {
        super(message);
    }
}
