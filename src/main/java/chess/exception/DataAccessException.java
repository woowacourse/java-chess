package chess.exception;

public class DataAccessException extends RuntimeException {

    private static final String MESSAGE = "DB 접근에 에러가 발생했습니다.";

    public DataAccessException(Throwable e) {
        super(MESSAGE, e);
    }

    public DataAccessException(String message) {
        super(message);
    }

}
