package chess.model.exception;

public class EndCantExecuteException extends ChessException{

    public EndCantExecuteException() {
        super(ChessExceptionType.END_CANT_EXECUTE);
    }
}
