package chess.domain.exceptions;

public class ChessPlayException extends RuntimeException {
    public ChessPlayException(final String message) {
        super(message);
    }
}
