package chess.exception;

public class WrongMoveCommandException extends IllegalArgumentException {
    public WrongMoveCommandException() {
        super("움직일 수 있는 말이 없습니다.");
    }
}