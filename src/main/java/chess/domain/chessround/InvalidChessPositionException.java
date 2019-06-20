package chess.domain.chessround;

public class InvalidChessPositionException extends RuntimeException {
    public InvalidChessPositionException(String message) {
        super(message);
    }
}
