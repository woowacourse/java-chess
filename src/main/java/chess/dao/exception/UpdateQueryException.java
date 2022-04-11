package chess.dao.exception;

public class UpdateQueryException extends RuntimeException{
    private static final String MESSAGE = "Update Query가 실행될 수 없습니다.";

    public UpdateQueryException() {
        super(MESSAGE);
    }
}
