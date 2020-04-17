package chess.exception;

public class MovingDirectionException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "잘못된 이동 방향입니다.";

    public MovingDirectionException() {
        super(ERROR_MESSAGE);
    }
}
