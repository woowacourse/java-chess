package chess.exception;

public class IllegalCommandException extends RuntimeException {
    private static final String ERROR_MESSAGE = "올바른 커맨드를 입력해주세요.";

    public IllegalCommandException() {
        super(ERROR_MESSAGE);
    }
}
