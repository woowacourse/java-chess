package chess.domain.chess.exception;

public class IllegalPositionException extends RuntimeException {
    public IllegalPositionException(String msg) {
        super(msg);
    }
}
