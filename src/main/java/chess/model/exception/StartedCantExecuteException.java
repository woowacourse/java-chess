package chess.model.exception;

public class StartedCantExecuteException extends ChessException {

    public StartedCantExecuteException() {
        super(ChessExceptionType.STARTED_CANT_EXECUTE);
    }
}
