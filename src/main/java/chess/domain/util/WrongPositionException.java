package chess.domain.util;

public class WrongPositionException extends RuntimeException {
    public static final String ERROR_POSITION_MESSAGE = "옳지 않은 좌표 입력입니다.";

    public WrongPositionException(String value) {
        super(ERROR_POSITION_MESSAGE + " 입력 : " + value);
    }

    public WrongPositionException() {
        super(ERROR_POSITION_MESSAGE);
    }
}
