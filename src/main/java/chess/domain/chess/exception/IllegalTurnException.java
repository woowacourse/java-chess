package chess.domain.chess.exception;

public class IllegalTurnException extends RuntimeException {
    public IllegalTurnException(String msg) {
        super(msg);
    }
}
