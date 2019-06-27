package chess.domain.chess.exception;

public class IllegalTeamException extends RuntimeException {
    public IllegalTeamException(String msg) {
        super(msg);
    }
}
