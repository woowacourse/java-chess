package domain.exception;

public class CannotStartException extends RuntimeException {
    public CannotStartException() {
        super("게임이 이미 실행되어 시작할 수 없습니다.");
    }
}
