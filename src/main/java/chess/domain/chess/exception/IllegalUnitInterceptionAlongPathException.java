package chess.domain.chess.exception;

public class IllegalUnitInterceptionAlongPathException extends IllegalChessMovingException {
    public IllegalUnitInterceptionAlongPathException(String msg) {
        super(msg);
    }
}
