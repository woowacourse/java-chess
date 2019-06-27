package chess.model.exception;

public class SameTwoPointsException extends RuntimeException {
    public SameTwoPointsException(final String message) {
        super(message);
    }
}
