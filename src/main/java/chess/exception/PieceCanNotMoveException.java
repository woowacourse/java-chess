package chess.exception;

public class PieceCanNotMoveException extends RuntimeException {
    private static final String ERROR_MESSAGE = "이동할 수 없는 말입니다.";

    public PieceCanNotMoveException() {
        super(ERROR_MESSAGE);
    }

    public PieceCanNotMoveException(String message) {
        super(message);
    }
}
