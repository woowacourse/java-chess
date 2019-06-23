package chess.domain.board;

public class InvalidColumnException extends RuntimeException {
    public InvalidColumnException(String message) {
        super(message);
    }
}
