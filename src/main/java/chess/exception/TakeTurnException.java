package chess.exception;

public class TakeTurnException extends IllegalArgumentException {
    public TakeTurnException(String message) {
        super(message);
    }
}
