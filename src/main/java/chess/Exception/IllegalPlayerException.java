package chess.Exception;

public class IllegalPlayerException extends IllegalArgumentException {
    public IllegalPlayerException() {
        super("처리할 수 없는 사용자입니다.");
    }
}
