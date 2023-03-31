package chess.model.exception;

public class QueryFailException extends ChessException {

    public QueryFailException() {
        super(ChessExceptionType.QUERY_FAIL);
    }
}
