package chess.exception;

public class TargetSameTeamException extends CustomException {
    public TargetSameTeamException(ErrorCode errorCode) {
        super(errorCode);
    }
}
