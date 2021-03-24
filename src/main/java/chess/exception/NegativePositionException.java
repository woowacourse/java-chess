package chess.exception;

public class NegativePositionException extends IllegalArgumentException {
    public NegativePositionException() {
        super("좌표에는 음수가 들어갈 수 없습니다.");
    }
}
