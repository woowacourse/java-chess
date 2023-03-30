package chess.model.exception;

public class CantMoveToSameColor extends ChessException {

    public CantMoveToSameColor() {
        super(ChessExceptionType.CANT_MOVE_TO_SAME_COLOR);
    }
}
