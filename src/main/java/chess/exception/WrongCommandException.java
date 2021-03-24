package chess.exception;

public class WrongCommandException extends IllegalArgumentException {
    public WrongCommandException() {
        super("잘못된 커멘드 입력입니다.");
    }
}
