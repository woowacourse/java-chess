package chess.exception;

public class ImpossibleMoveException extends ChessException {
    public ImpossibleMoveException() {
        this("이동할 수 없는 위치입니다.");
    }

    public ImpossibleMoveException(String message) {
        super(message);
    }
}
