package chess.domain.exceptions;

public class InvalidRouteException extends ChessPlayException {
    public InvalidRouteException(final String message) {
        super(message);
    }
}
