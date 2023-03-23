package chess.domain.exception;

public abstract class ChessGameException extends RuntimeException {

    public ChessGameException(String message) {
        super(message);
    }
}
