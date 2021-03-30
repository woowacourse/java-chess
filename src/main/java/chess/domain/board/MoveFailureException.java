package chess.domain.board;

public class MoveFailureException extends RuntimeException {

    public MoveFailureException(final String message) {
        super(message);
    }
}
