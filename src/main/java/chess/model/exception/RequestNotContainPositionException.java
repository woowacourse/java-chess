package chess.model.exception;

public class RequestNotContainPositionException extends ChessException {

    public RequestNotContainPositionException() {
        super(ChessExceptionType.REQUEST_NOT_CONTAIN_POSITION);
    }
}
