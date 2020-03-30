package chess.exception;

public class PawnNotAttackableException extends IllegalArgumentException {

    public PawnNotAttackableException(String message) {
        super(message);
    }
}
