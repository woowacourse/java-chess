package chess.domain.piece.exception;

public class InvalidMoveException extends IllegalStateException {

    private static final String message = "해당 기물은 주어진 좌표로 이동할 수 없습니다.";

    public InvalidMoveException() {
        super(message);
    }
}
