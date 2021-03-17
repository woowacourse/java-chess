package chess.domain.position.exception;

public class InvalidColumnException extends PositionException {
    public static final String INVALID_COLUMN_NAME_MESSAGE = "유효하지 않은 열입니다.";

    public InvalidColumnException() {
        super(INVALID_COLUMN_NAME_MESSAGE);
    }
}
