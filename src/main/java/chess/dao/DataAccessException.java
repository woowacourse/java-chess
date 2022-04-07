package chess.dao;

public class DataAccessException extends RuntimeException {
    private final static String MESSAGE = "쿼리 실행에 실패했습니다.";

    public DataAccessException() {
        super(MESSAGE);
    }
}
