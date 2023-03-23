package chess.domain.exception;

public class IllegalMoveException extends ChessGameException {

    public IllegalMoveException(String message) {
        super(message);
    }
}
