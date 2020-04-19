package chess.webController;

public class MoveException extends RuntimeException {
    public MoveException(String message) {
        super(message);
    }
}
