package chess.domain.chess.game;

public class IllegalTurnException extends RuntimeException {
    public IllegalTurnException(String msg) {
        super(msg);
    }
}
