package chess.model.exceptions;

public class IllegalXPositionException extends RuntimeException {
    public IllegalXPositionException(final String message) {
        super(message);
    }
}