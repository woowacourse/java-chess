package chess.domain.board;

public class InvalidTileException extends RuntimeException {
    public InvalidTileException(String message) {
        super(message);
    }
}
