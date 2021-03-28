package chess.domain.position.exception;

public class InvalidRowException extends PositionException {
    public static final String INVALID_ROW_NAME_MESSAGE = "유효하지 않은 행입니다.";

    public InvalidRowException() {
        super(INVALID_ROW_NAME_MESSAGE);
    }
}
