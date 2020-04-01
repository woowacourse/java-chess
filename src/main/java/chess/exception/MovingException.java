package chess.exception;

public class MovingException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "이동할 수 없는 position입니다.";

    public MovingException() {
        super(ERROR_MESSAGE);
    }
}
