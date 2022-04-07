package chess.exception;

public class SelectQueryException extends RuntimeException {

    private static final String MESSAGE = "Select 요청에 실패했습니다.";

    public SelectQueryException() {
        super(MESSAGE);
    }
}
