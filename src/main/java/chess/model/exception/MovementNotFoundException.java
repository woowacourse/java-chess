package chess.model.exception;

public class MovementNotFoundException extends ChessException {

    public MovementNotFoundException() {
        super(ChessExceptionType.MOVEMENT_NOT_FOUND);
    }
}
