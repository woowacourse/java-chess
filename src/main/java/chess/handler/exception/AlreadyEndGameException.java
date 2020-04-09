package chess.handler.exception;

public class AlreadyEndGameException extends RuntimeException {
    public AlreadyEndGameException(String message) {
        super(message);
    }
}
