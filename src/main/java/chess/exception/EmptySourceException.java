package chess.exception;

public class EmptySourceException extends InvalidMovementException {

    private static final String EXCEPTION_MESSAGE_FORMAT = "이동시키고자 하는 말이 존재하지 않습니다. 입력값 : %s";

    public EmptySourceException(String sourceName) {
        super(String.format(EXCEPTION_MESSAGE_FORMAT, sourceName));
    }
}
