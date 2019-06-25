package chess.model;

public class AttackMyTeamException extends RuntimeException {
    public AttackMyTeamException(final String message) {
        super(message);
    }
}
