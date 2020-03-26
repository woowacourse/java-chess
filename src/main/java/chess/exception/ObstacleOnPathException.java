package chess.exception;

public class ObstacleOnPathException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "이동 경로에 장애물이 있습니다.";

    public ObstacleOnPathException() {
        super(ERROR_MESSAGE);
    }
}
