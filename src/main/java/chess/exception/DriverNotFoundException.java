package chess.exception;

public class DriverNotFoundException extends RuntimeException{

    public DriverNotFoundException() {
    }

    public DriverNotFoundException(String message) {
        super(message);
    }

    public DriverNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
