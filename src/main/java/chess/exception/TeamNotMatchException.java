package chess.exception;

public class TeamNotMatchException extends CustomException {
    public TeamNotMatchException(ErrorCode errorCode) {
        super(errorCode);
    }
}
