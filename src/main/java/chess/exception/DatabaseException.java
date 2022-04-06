package chess.exception;

// TODO: 더 명확하게 예외 이름과 메세지를 변경해야함.
public class DatabaseException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "데이터베이스 작업이 실패했습니다.";

    public DatabaseException() {
        super(EXCEPTION_MESSAGE);
    }
}
