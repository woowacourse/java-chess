package chess.model.exception;

public class CantMoveFromToException extends ChessException {

    public CantMoveFromToException() {
        super(ChessExceptionType.CANT_MOVE_FROM_TO);
    }
}
