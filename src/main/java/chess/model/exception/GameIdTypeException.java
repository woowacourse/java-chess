package chess.model.exception;

public class GameIdTypeException extends ChessException {

    public GameIdTypeException() {
        super(ChessExceptionType.GAME_ID_ILLEGAL_TYPE);
    }
}
