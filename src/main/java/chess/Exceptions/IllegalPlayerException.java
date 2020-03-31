package chess.Exceptions;

public class IllegalPlayerException extends RuntimeException {
    public IllegalPlayerException() {
        super("처리할 수 없는 사용자입니다.");
    }
}
