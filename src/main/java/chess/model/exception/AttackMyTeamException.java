package chess.model.exception;

public class AttackMyTeamException extends RuntimeException {
    public AttackMyTeamException(final String message) {
        super(message);
    }
}
