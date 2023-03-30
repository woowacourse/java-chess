package chess.model.exception;

public class RequestNotContainIdException extends ChessException{

    public RequestNotContainIdException() {
        super(ChessExceptionType.REQUEST_NOT_CONTAIN_ID);
    }
}
