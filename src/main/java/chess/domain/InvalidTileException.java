package chess.domain;

public class InvalidTileException extends RuntimeException {
    public InvalidTileException(String message) {
        super(message);
    }
}
