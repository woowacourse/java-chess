package chess.domain.util;

public class WrongOperationException extends RuntimeException {
    public WrongOperationException() {
        this("수행할 수 없는 명령입니다.");
    }

    public WrongOperationException(String message) {
        super(message);
    }
}
