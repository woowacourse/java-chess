package chess.exception;

public class DriverNotFoundException extends RuntimeException {

    public DriverNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
