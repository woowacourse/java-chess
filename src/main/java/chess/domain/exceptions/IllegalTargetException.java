package chess.domain.exceptions;

public class IllegalTargetException extends ChessPlayException {
    public IllegalTargetException(final String message) {
        super(message);
    }
}
