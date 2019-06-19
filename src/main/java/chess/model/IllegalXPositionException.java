package chess.model;

public class IllegalXPositionException extends RuntimeException {
    public IllegalXPositionException(final String message) {
        super(message);
    }
}