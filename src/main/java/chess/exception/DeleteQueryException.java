package chess.exception;

public class DeleteQueryException extends RuntimeException {

    private static final String MESSAGE = "Delete 요청에 실패했습니다.";

    public DeleteQueryException() {
        super(MESSAGE);
    }
}
