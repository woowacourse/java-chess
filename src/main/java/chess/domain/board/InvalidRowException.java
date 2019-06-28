package chess.domain.board;

public class InvalidRowException extends RuntimeException {
    public InvalidRowException(String message) {
        super(message);
    }
}
