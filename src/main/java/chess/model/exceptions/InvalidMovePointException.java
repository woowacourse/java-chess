package chess.model.exceptions;

public class InvalidMovePointException extends RuntimeException {
    public InvalidMovePointException(final String message) {
        super(message);
    }
}
