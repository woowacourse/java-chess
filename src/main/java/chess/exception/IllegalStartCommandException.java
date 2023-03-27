package chess.exception;

public class IllegalStartCommandException extends RuntimeException {
    private static final String ERROR_MESSAGE = "START COMMAND를 입력해주세요";

    public IllegalStartCommandException() {
        super(ERROR_MESSAGE);
    }
}
