package chess.domain.util;

public class WrongOperationException extends RuntimeException {
    public WrongOperationException(String message) {
        super(message);
    }

    public WrongOperationException() {
        this("수행할 수 없는 명령입니다.");
    }
}
