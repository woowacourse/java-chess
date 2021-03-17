package chess.domain.exceptions;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
