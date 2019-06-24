package chess.domain.board;

public class GameOverException extends RuntimeException {
    public GameOverException(String message) {
        super(message);
    }
}
