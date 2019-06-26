package chess.model.exceptions;

public class AttackMyTeamException extends RuntimeException {
    public AttackMyTeamException(final String message) {
        super(message);
    }
}
