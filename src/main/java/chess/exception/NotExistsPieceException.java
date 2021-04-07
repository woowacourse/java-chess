package chess.exception;

public class NotExistsPieceException extends RuntimeException {
    public NotExistsPieceException(String message) {
        super(message);
    }
}
