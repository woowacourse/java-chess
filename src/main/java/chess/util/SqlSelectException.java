package chess.util;

public class SqlSelectException extends RuntimeException {

    private final static String MESSAGE = "[ERROR] SELECT 쿼리를 실행할 수 없습니다.";

    public SqlSelectException() {
        super(MESSAGE);
    }
}
