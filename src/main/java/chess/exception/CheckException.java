package chess.exception;

public class CheckException extends InvalidMovementException {

    private static final String EXCEPTION_MESSAGE = "왕을 방어하세요.";

    public CheckException() {
        super(EXCEPTION_MESSAGE);
    }
}
