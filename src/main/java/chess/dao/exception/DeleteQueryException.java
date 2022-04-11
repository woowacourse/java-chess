package chess.dao.exception;

public class DeleteQueryException extends RuntimeException{
    private static final String MESSAGE = "Delete Query가 실행될 수 없습니다.";

    public DeleteQueryException() {
        super(MESSAGE);
    }
}
