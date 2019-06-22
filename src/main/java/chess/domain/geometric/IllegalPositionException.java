package chess.domain.geometric;

public class IllegalPositionException extends RuntimeException {
    IllegalPositionException(String msg) {
        super(msg);
    }
}
