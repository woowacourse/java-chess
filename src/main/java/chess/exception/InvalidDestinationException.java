package chess.exception;

public class InvalidDestinationException extends InvalidMovementException {

    private static final String EXCEPTION_MESSAGE = "해당 말의 규칙 상 이동할 수 없는 목적지입니다.";

    public InvalidDestinationException() {
        super(EXCEPTION_MESSAGE);
    }
}
