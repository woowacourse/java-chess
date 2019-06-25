package chess.model;

public class InvalidRangeException extends RuntimeException{
    public InvalidRangeException(String message) {
        super(message);
    }
}
