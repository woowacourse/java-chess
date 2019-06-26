package chess.model.coordinate;

public class IllegalXPositionException extends RuntimeException {
    public IllegalXPositionException(final String message) {
        super(message);
    }
}