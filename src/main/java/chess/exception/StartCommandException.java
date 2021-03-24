package chess.exception;

public class StartCommandException extends IllegalArgumentException {
    public StartCommandException() {
        super("게임을 시작해주세요");
    }
}
