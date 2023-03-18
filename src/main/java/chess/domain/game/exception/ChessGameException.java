package chess.domain.game.exception;

public class ChessGameException extends RuntimeException {

    public ChessGameException(String message) {
        super(message);
    }

    public ChessGameException(String message, Throwable cause) {
        super(message, cause);
    }
}
