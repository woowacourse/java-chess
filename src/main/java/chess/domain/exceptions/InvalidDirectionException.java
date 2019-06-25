package chess.domain.exceptions;

public class InvalidDirectionException extends ChessPlayException {
    public InvalidDirectionException(final String message) {
        super(message);
    }
}
