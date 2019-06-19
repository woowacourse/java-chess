package chess.domain.exceptions;

public class InvalidDistanceException extends RuntimeException {
    public InvalidDistanceException(final String message) {
        super(message);
    }
}
