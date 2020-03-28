package chess.exception;

public class MovingToAllyException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "아군의 말 위치로는 이동할 수 없습니다.";

    public MovingToAllyException() {
        super(ERROR_MESSAGE);
    }
}
