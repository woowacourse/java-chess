package chess.exception;

public class WrongInitPositionException extends IllegalStateException {
    public WrongInitPositionException() {
        super("잘못된 초기 위치입니다.");
    }
}
