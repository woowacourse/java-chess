package chess.model.exception;

public class EmptyCantExecuteException extends ChessException {

    public EmptyCantExecuteException() {
        super(ChessExceptionType.EMPTY_CANT_EXECUTE);
    }
}
