package chess.exception;

public class TargetSameTeamException extends RuntimeException {
    private static final String ERROR_MESSAGE = "타겟 지점의 말이 같은 진영의 말입니다.";

    public TargetSameTeamException() {
        super(ERROR_MESSAGE);
    }
}
