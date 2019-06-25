package chess.model;

public class InvalidMovePointException extends RuntimeException {
    public InvalidMovePointException(final String message) {
        super(message);
    }
}
