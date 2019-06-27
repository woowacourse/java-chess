package chess.exception;

public class NotFoundPositionException extends IllegalArgumentException {
    public NotFoundPositionException() {
        super("존재하지 않는 좌표입니다.");
    }

    public NotFoundPositionException(String s) {
        super(s);
    }
}
