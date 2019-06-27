package chess.domain.exceptions;

public class IllegalSourceException extends RuntimeException {
    public IllegalSourceException(final String message) {
        super(message);
    }
}
