package chess.model.exception;

public class GameCommandNotFoundException extends ChessException {

    public GameCommandNotFoundException() {
        super(ChessExceptionType.COMMAND_NOT_FOUND);
    }
}
