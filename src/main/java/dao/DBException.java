package dao;

public class DBException extends RuntimeException {
    private static final String DEFAULT_ERROR_MESSAGE = "쿼리 실행 중 오류가 발생했습니다.";

    public DBException(String message) {
        super(message);
    }

    public DBException(Throwable cause) {
        super(DEFAULT_ERROR_MESSAGE, cause);
    }
}
