package chess.exception;

public class InvalidGameException extends ChessException {
    private static final String MESSAGE = "게임을 찾을 수 없습니다.";

    public InvalidGameException() {
        super(MESSAGE);
    }

}
