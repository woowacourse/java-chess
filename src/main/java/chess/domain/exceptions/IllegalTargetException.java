package chess.domain.exceptions;

public class IllegalTargetException extends RuntimeException {
    public IllegalTargetException(final String message) {
        super(message);
    }
}
