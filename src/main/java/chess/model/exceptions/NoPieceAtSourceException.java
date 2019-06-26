package chess.model.exceptions;

public class NoPieceAtSourceException extends RuntimeException {
    public NoPieceAtSourceException(String message) {
        super(message);
    }
}
