package chess.exception;

public class MovingDistanceException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "이동할 수 없는 거리입니다.";

    public MovingDistanceException() {
        super(ERROR_MESSAGE);
    }
}
