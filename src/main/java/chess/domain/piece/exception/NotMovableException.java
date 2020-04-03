package chess.domain.piece.exception;

public class NotMovableException extends RuntimeException {
    private static final String NOT_MOVABLE_MESSAGE = "해당 방향으로 이동할 수 없습니다.";

    public NotMovableException() {
        super(NOT_MOVABLE_MESSAGE);
    }

    public NotMovableException(String message) {
        super(message);
    }
}
