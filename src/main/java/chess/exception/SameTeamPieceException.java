package chess.exception;

public class SameTeamPieceException extends IllegalArgumentException {

    public SameTeamPieceException(String message) {
        super(message);
    }
}
