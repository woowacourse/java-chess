package chess.exception;

public class ImpossibleMoveException extends ChessException {
    public ImpossibleMoveException() {
        super("이동할 수 없는 위치입니다.");
    }
}
