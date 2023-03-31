package chess.dao.exception;

public class DatabaseQueryException extends RuntimeException {

    private static final String WRONG_QUERY_ERROR_MESSAGE_FORMAT = "DB 쿼리 오류: %s \nSQL: %s";

    public DatabaseQueryException(final String message, final String query) {
        super(String.format(WRONG_QUERY_ERROR_MESSAGE_FORMAT, message, query));
    }


}
