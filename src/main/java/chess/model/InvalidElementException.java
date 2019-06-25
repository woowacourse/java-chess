package chess.model;

public class InvalidElementException extends RuntimeException{
    public InvalidElementException(String message) {
        super(message);
    }
}
