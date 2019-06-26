package chess.model.exceptions;

public class InvalidTurnException extends RuntimeException {
    public InvalidTurnException(String message) {
        super(message);
    }
}
