package chess.domain.exceptions;

public class InvalidRouteException extends RuntimeException {
    public InvalidRouteException(final String message) {
        super(message);
    }
}
