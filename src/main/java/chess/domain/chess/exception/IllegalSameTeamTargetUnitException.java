package chess.domain.chess.exception;

public class IllegalSameTeamTargetUnitException extends IllegalChessMovingException {
    public IllegalSameTeamTargetUnitException(String msg) {
        super(msg);
    }
}
