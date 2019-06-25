package chess.domain.exceptions;

public class IllegalSourceException extends ChessPlayException {
    public IllegalSourceException(final String message) {
        super(message);
    }
}
