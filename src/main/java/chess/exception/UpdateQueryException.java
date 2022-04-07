package chess.exception;

public class UpdateQueryException extends RuntimeException {

    private static final String MESSAGE = "Update 요청에 실패했습니다.";

    public UpdateQueryException() {
        super(MESSAGE);
    }
}
