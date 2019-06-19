package chess.domain;

public class InvalidColumnException extends RuntimeException {
    public InvalidColumnException(String message) {
        super(message);
    }
}
