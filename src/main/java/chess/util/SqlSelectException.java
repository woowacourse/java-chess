package chess.util;

public class SqlSelectException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] SELECT 쿼리를 실행할 수 없습니다.";

    public SqlSelectException(final String message) {
        super(message);
    }

    public SqlSelectException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
