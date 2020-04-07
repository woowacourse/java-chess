package chess.domain.exception;

public class WrongOperationException extends RuntimeException {
    public static final String ERROR_OPERATION_MESSAGE = "수행할 수 없는 명령입니다.";

    public WrongOperationException(String value) {
        super(String.format("%s 입력 : %s", ERROR_OPERATION_MESSAGE, value));
    }

    public WrongOperationException() {
        super(ERROR_OPERATION_MESSAGE);
    }
}
