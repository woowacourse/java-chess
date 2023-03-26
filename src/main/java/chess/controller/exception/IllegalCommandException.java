package chess.controller.exception;

public class IllegalCommandException extends RuntimeException {

    public IllegalCommandException(String message) {
        super(message);
    }
}
