package chess.domain.chess.exception;

public class SameTeamTargetUnitException extends ChessMovingException {
    public SameTeamTargetUnitException(String msg) {
        super(msg);
    }
}
