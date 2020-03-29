package chess.exception;

public class MoveCommandWhenBoardNullException extends NullPointerException {
    public MoveCommandWhenBoardNullException(String message) {
        super(message);
    }
}
