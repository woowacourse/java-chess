package chess.model.exception;

public class FromIsEmptyException extends ChessException {

    public FromIsEmptyException() {
        super(ChessExceptionType.FROM_IS_EMPTY);
    }
}
