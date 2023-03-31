package chess.exception;

public class IllegalStartCommandException extends CustomException {
    public IllegalStartCommandException(ErrorCode errorCode) {
        super(errorCode);
    }
}
