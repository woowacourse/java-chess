package chess.exception;

public class NotFoundChessGameException extends RuntimeException {
    private static final String MESSAGE = "체스게임을 찾을 수 없습니다.";

    public NotFoundChessGameException() {
        super(MESSAGE);
    }
}
