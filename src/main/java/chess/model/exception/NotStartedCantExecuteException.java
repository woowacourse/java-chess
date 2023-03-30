package chess.model.exception;

public class NotStartedCantExecuteException extends ChessException {

    public NotStartedCantExecuteException() {
        super(ChessExceptionType.NOT_STARTED_CANT_EXECUTE);
    }
}
