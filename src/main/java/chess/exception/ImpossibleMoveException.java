package chess.exception;

public class ImpossibleMoveException extends ChessException {

    private static final String MESSAGE = "타깃 장소로 움직일 수 없습니다.";

    public ImpossibleMoveException() {
        super(MESSAGE);
    }
}
