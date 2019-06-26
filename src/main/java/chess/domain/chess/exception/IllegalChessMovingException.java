package chess.domain.chess.exception;

public class IllegalChessMovingException extends RuntimeException {
    public IllegalChessMovingException(String msg) {
        super(msg);
    }
}
