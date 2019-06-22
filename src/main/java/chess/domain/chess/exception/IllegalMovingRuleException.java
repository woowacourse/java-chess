package chess.domain.chess.exception;

public class IllegalMovingRuleException extends ChessMovingException {
    public IllegalMovingRuleException(String msg) {
        super(msg);
    }
}
