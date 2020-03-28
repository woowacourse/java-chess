package chess.domain.chessBoard;

public class KingCatchException extends RuntimeException {
    public KingCatchException() {
    }

    public KingCatchException(String message) {
        super(message);
    }
}
