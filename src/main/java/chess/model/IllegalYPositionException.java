package chess.model;

public class IllegalYPositionException extends RuntimeException {
    public IllegalYPositionException(final String message) {
        super(message);
    }
}
