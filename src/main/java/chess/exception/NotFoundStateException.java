package chess.exception;

public class NotFoundStateException extends RuntimeException {
    private static final String MESSAGE = "존재하지 않는 상태입니다.";

    public NotFoundStateException() {
        super(MESSAGE);
    }

}
