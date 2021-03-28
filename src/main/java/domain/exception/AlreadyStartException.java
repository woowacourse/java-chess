package domain.exception;

public class AlreadyStartException extends RuntimeException {
    public AlreadyStartException() {
        super("게임이 이미 실행되어 시작할 수 없습니다.");
    }
}
