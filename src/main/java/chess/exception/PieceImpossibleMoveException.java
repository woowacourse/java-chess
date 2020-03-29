package chess.exception;

public class PieceImpossibleMoveException extends IllegalArgumentException {
    public PieceImpossibleMoveException(String message) {
        super(message);
    }
}
