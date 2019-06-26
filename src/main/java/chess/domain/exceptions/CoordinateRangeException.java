package chess.domain.exceptions;

public class CoordinateRangeException extends ChessPlayException {
    public CoordinateRangeException(final String message) {
        super(message);
    }
}
