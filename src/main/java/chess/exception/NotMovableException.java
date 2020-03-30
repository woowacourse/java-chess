package chess.exception;

public class NotMovableException extends IllegalArgumentException {

    public NotMovableException(String message) {
        super(message);
    }
}
