package chess.util;

public class SqlConnectionException extends RuntimeException {

    public final static String MESSAGE = "[ERROR] Connection 연결에 실패했습니다.";

    public SqlConnectionException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
