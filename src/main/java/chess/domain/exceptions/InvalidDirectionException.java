package chess.domain.exceptions;

public class InvalidDirectionException extends RuntimeException {
    public InvalidDirectionException(final String message) {
        super(message);
    }
}
