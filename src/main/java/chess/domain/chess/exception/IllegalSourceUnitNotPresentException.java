package chess.domain.chess.exception;

public class IllegalSourceUnitNotPresentException extends IllegalChessMovingException {

    public IllegalSourceUnitNotPresentException(String msg) {
        super(msg);
    }
}
