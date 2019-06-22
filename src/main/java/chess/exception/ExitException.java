package chess.exception;

public class ExitException extends RuntimeException {
    public ExitException(final String message) {
        super(message);
    }
}
