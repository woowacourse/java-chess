package chess.exception;

public class InvalidMovePositionException extends IllegalArgumentException {
    public InvalidMovePositionException() {
        super("해당 말이 갈 수 없는 좌표입니다.");
    }
}
