package chess.exception;

public class GameOverException extends RuntimeException {
    public GameOverException(String message) {
        super(message);
    }
}
