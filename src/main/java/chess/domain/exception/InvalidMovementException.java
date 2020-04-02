package chess.domain.exception;

public class InvalidMovementException extends RuntimeException {

    private static final String CANNOT_MOVE_MESSAGE = "이동할 수 없습니다.";

    public InvalidMovementException() {
        super(CANNOT_MOVE_MESSAGE);
    }

    public InvalidMovementException(String message) {
        super(String.format("%s\n%s", CANNOT_MOVE_MESSAGE, message));
    }
}
