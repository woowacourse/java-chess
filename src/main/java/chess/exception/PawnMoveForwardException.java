package chess.exception;

public class PawnMoveForwardException extends CustomException {
    public PawnMoveForwardException(ErrorCode errorCode) {
        super(errorCode);
    }
}
