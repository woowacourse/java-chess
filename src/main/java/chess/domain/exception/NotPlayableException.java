package chess.domain.exception;

public class NotPlayableException extends ChessGameException {
    public NotPlayableException(String message) {
        super(message);
    }
}
