package chess.domain.exception;

public class DifferentTeamException extends ChessGameException {
    
    public DifferentTeamException(String message) {
        super(message);
    }
}
