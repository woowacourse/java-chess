package chess.domain.chess.exception;

public class IllegalMovingRuleException extends IllegalChessMovingException {
    public IllegalMovingRuleException(String msg) {
        super(msg);
    }
}
