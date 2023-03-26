package chess.exception;

public class PathBlockedException extends RuntimeException {
    private static final String ERROR_MESSAGE = "이동 경로에 말이 존재합니다.";

    public PathBlockedException() {
        super(ERROR_MESSAGE);
    }
}
