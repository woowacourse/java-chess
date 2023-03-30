package chess.model.exception;

public class RankNotFoundException extends ChessException {

    public RankNotFoundException() {
        super(ChessExceptionType.RANK_NOT_FOUND);
    }
}
