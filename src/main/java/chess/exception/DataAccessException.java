package chess.exception;

public class DataAccessException extends RuntimeException {
    public DataAccessException() {
        super("데이터에 접근할 수 없습니다.");
    }
}
