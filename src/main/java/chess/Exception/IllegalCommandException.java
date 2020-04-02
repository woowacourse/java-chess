package chess.Exception;

public class IllegalCommandException extends RuntimeException {
    public IllegalCommandException() {
        super("잘못된 명령어 입니다.");
    }
}
