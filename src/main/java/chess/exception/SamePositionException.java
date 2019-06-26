package chess.exception;

public class SamePositionException extends RuntimeException {
    public SamePositionException() {
        super("같은 위치에 말을 놓을 수 없습니다.");
    }
}
