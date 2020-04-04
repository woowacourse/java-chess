package chess.Exception;

public class IllegalMoveException extends IllegalArgumentException {
    public IllegalMoveException(String message) {
        super(message);
    }
}
