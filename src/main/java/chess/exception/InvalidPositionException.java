package chess.exception;

public class InvalidPositionException extends RuntimeException {
    private static final String INVALID_POSITION_EXCEPTION_MESSAGE_FORMAT = "위치 입력값이 올바르지 않습니다. 입력값 : %s";

    public InvalidPositionException(String inputKey) {
        super(String.format(INVALID_POSITION_EXCEPTION_MESSAGE_FORMAT, inputKey));
    }
}
