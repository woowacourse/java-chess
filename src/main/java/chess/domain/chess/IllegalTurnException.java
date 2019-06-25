package chess.domain.chess;

public class IllegalTurnException extends RuntimeException {
    public IllegalTurnException(String msg) {
        super(msg);
    }
}
