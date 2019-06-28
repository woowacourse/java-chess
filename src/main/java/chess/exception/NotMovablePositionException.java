package chess.exception;

public class NotMovablePositionException extends IllegalArgumentException {
    public NotMovablePositionException() {
        super("이동할 수 없는 위치입니다.");
    }

    public NotMovablePositionException(String s) {
        super(s);
    }
}
