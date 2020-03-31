package chess.domain.util;

public class WrongPositionException extends RuntimeException {
    public WrongPositionException(String message) {
        super(message);
    }

    public WrongPositionException() {
        this("옳지 않은 좌표 입력입니다.");
    }
}
