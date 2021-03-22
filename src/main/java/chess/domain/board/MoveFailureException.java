package chess.domain.board;

public class MoveFailureException extends RuntimeException {

    public MoveFailureException(String message) {
        super(message);
    }
}
