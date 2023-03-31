package chess.exception;

public class PawnMoveDiagonalException extends CustomException {
    public PawnMoveDiagonalException(ErrorCode errorCode) {
        super(errorCode);
    }
}
