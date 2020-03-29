package chess.exception;

public class BlankMoveUnsupportedException extends UnsupportedOperationException {
    public BlankMoveUnsupportedException(String message) {
        super(message);
    }
}
