package chess.model.exception;

public class DataBaseCanNotConnectException extends ChessException {

    public DataBaseCanNotConnectException() {
        super(ChessExceptionType.DATABASE_CAN_NOT_CONNECT);
    }
}
