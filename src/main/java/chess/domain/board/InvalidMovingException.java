package chess.domain.board;

public class InvalidMovingException extends RuntimeException {
    public InvalidMovingException(String message) {
        super(message);
    }
}
