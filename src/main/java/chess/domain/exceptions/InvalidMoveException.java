package chess.domain.exceptions;

import java.util.function.Supplier;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException() {

    }

    public InvalidMoveException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
