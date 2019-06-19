package chess.domain;

public class InvalidPieceTypeException extends RuntimeException {
    public InvalidPieceTypeException(String message) {
        super(message);
    }
}
