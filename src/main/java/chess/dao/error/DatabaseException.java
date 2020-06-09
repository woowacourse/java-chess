package chess.dao.error;

public class DatabaseException extends RuntimeException {
    public static final String CONNECTION_FAILED_MESSAGE = "데이터베이스 연결에 실패했습니다.";
    public static final String DEPENDENCY_NOT_FOUND_MESSAGE = "데이터베이스 의존성을 찾을 수 없습니다.";
    public static final String QUERY_FAILED_MESSAGE = "쿼리에 실패했습니다.";

    public DatabaseException(String message) {
        super(message);
    }
}
