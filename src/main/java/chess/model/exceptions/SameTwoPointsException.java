package chess.model.exceptions;

public class SameTwoPointsException extends RuntimeException {
    public SameTwoPointsException(final String message) {
        super(message);
    }
}
