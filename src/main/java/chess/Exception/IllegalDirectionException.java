package chess.Exception;

public class IllegalDirectionException extends IllegalArgumentException {
    public IllegalDirectionException() {
        super("이동 방식을 찾을 수 없습니다.");
    }
}
