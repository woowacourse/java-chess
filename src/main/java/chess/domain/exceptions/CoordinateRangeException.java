package chess.domain.exceptions;

public class CoordinateRangeException extends RuntimeException {
    public CoordinateRangeException(final String message) {
        super(message);
    }
}
