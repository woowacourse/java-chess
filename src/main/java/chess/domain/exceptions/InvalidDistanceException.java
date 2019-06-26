package chess.domain.exceptions;

public class InvalidDistanceException extends ChessPlayException {
    public InvalidDistanceException(final String message) {
        super(message);
    }
}
