package chess.exception;

public class InsertQueryException extends RuntimeException {

    private static final String MESSAGE = "Insert 요청에 실패했습니다.";

    public InsertQueryException() {
        super(MESSAGE);
    }
}
