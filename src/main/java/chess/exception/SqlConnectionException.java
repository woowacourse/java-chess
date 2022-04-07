package chess.exception;

public class SqlConnectionException extends RuntimeException {

    private static final String MESSAGE = "Connection 연결에 실패했습니다.";

    public SqlConnectionException() {
        super(MESSAGE);
    }
}
