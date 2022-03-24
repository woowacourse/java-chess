package chess.exception;

public class UnmovableException extends IllegalArgumentException {

    public UnmovableException(String message) {
        super(message);
    }
}
