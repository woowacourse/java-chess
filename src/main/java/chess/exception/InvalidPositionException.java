package chess.exception;

public class InvalidPositionException extends IllegalArgumentException {
    public InvalidPositionException(String message) {
        super(message);
    }
}
