package chess.domain.chess.exception;

public class IllegalPawnMovingRuleException extends IllegalChessMovingException {
    public IllegalPawnMovingRuleException(String msg) {
        super(msg);
    }
}
