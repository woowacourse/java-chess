package chess.model.exception;

public class NoPieceAtSourceException extends RuntimeException {
    public NoPieceAtSourceException(String message) {
        super(message);
    }
}
