package chess.domain;

public class InvalidRowException extends RuntimeException {
    public InvalidRowException(String message) {
        super(message);
    }
}
