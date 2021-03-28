package domain.exception;

public class GameNotStartException extends RuntimeException {
    public GameNotStartException() {
        super("게임이 시작되지 않아 실행할 수 없습니다.");
    }
}
