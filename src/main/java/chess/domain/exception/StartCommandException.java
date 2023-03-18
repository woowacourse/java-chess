package chess.domain.exception;

public class StartCommandException extends IllegalArgumentException {

    public StartCommandException() {
        super("아직 게임을 시작하지 않았습니다.");
    }
}
