package chess.model.exceptions;

public class IllegalYPositionException extends RuntimeException {
    public IllegalYPositionException(final String message) {
        super(message);
    }
}
