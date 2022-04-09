package chess.dao.exception;

public class InsertQueryException extends RuntimeException{
    private static final String MESSAGE = "Insert Query가 실행될 수 없습니다.";

    public InsertQueryException() {
        super(MESSAGE);
    }
}
