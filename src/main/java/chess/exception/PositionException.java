package chess.exception;

public class PositionException extends ChessException {

    private static final String INVALID_POSITION_NAME_MESSAGE = "유효하지 않은 좌표입니다.";

    public PositionException() {
        super(INVALID_POSITION_NAME_MESSAGE);
    }

    public PositionException(String message) {
        super(message);
    }
}
