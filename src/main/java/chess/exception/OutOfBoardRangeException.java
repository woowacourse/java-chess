package chess.exception;

public class OutOfBoardRangeException extends IllegalArgumentException {
    public OutOfBoardRangeException() {
        super("체스 보드 판의 범위를 넘어섰습니다.");
    }
}
