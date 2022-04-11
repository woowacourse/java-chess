package chess.utils;

public class DataAccessException extends RuntimeException {

    private final static String MESSAGE = "쿼리가 정상적으로 실행되지 않았습니다.";

    public DataAccessException() {
        super(MESSAGE);
    }

    public DataAccessException(String message) {
        super(message);
    }
}
