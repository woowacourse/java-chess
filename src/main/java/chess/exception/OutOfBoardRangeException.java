package chess.exception;

public class OutOfBoardRangeException extends IllegalArgumentException {
    public OutOfBoardRangeException(String message) {
        super(message);
    }
}
