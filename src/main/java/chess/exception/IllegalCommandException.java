package chess.exception;

public class IllegalCommandException extends CustomException {
    public IllegalCommandException(ErrorCode errorCode) {
        super(errorCode);
    }
}
