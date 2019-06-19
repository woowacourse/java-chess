package chess.domain;

public class InvalidChessPositionException extends RuntimeException {
    public InvalidChessPositionException(String message) {
        super(message);
    }
}
