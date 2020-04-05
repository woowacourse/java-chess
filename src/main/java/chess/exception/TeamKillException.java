package chess.exception;

public class TeamKillException extends InvalidMovementException {

    private static final String EXCEPTION_MESSAGE = "본인의 말은 잡을 수 없습니다.";

    public TeamKillException() {
        super(EXCEPTION_MESSAGE);
    }
}
