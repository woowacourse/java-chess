package chess.domain.util;

public class WrongOperationException extends RuntimeException {
    public static final String ERROR_OPERATION_MESSAGE = "수행할 수 없는 명령입니다.";

    public WrongOperationException(String value) {
        super(ERROR_OPERATION_MESSAGE + " 입력 : " + value);
    }

    public WrongOperationException() {
        super(ERROR_OPERATION_MESSAGE);
    }
}
