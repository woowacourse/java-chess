package chess.domain.exceptions;

public class IllegalRouteException extends RuntimeException {
    public IllegalRouteException(final String message) {
        super(message);
    }
}
