package chess.util;

public class SqlConnectionException extends RuntimeException{

    private final static String MESSAGE = "[ERROR] Connection 연결에 실패했습니다.";

    public SqlConnectionException() {
        super(MESSAGE);
    }
}
