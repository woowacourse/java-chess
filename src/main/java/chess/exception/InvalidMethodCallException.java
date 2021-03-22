package chess.exception;

public class InvalidMethodCallException extends UnsupportedOperationException {

    private static final String INVALID_METHOD_CALL_MESSAGE = "유효하지 않은 메서드 호출입니다.";

    public InvalidMethodCallException() {
        super(INVALID_METHOD_CALL_MESSAGE);
    }
}
