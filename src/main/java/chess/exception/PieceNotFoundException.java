package chess.exception;

public class PieceNotFoundException extends IllegalArgumentException {

    public PieceNotFoundException(String message) {
        super(message);
    }
}
