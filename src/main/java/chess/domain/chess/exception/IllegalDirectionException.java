package chess.domain.chess.exception;

public class IllegalDirectionException extends RuntimeException {
    public IllegalDirectionException(String msg) {
        super(msg);
    }
}
