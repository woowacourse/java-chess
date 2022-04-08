package chess.util;

public class SqlUpdateException extends RuntimeException {

    public final static String SINGLE_UPDATE_FAILURE_MESSAGE = "[ERROR] 단건 업데이트에 실패했습니다.";
    public final static String MULTIPLE_UPDATE_FAILURE_MESSAGE = "[ERROR] 배치 업데이트에 실패했습니다.";

    public SqlUpdateException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
