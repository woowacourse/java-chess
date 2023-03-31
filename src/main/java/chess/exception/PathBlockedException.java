package chess.exception;

public class PathBlockedException extends CustomException {
    public PathBlockedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
