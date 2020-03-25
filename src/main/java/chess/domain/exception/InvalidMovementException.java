package chess.domain.exception;

public class InvalidMovementException extends RuntimeException {
    public InvalidMovementException() {
        super("이동할 수 없습니다.");
    }
}
