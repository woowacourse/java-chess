package chess.domain.exception;

public class IllegalGameStateException extends IllegalStateException {
    public IllegalGameStateException() {
        super("해당 상태에서 진행할 수 없는 명령입니다.");
    }
}
