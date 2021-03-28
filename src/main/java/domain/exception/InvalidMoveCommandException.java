package domain.exception;

public class InvalidMoveCommandException extends RuntimeException {
    public InvalidMoveCommandException(String command) {
        super("명령이 잘못되었습니다 (입력 : " + command + ")");
    }
}
