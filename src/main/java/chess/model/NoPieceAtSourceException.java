package chess.model;

public class NoPieceAtSourceException extends RuntimeException {
    public NoPieceAtSourceException(String message) {
        super(message);
    }
}
