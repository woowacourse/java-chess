package chess.model.exception;

public class IsNotPieceTurnException extends ChessException {

    public IsNotPieceTurnException() {
        super(ChessExceptionType.IS_NOT_PIECE_TURN);
    }
}
