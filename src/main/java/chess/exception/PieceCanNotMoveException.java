package chess.exception;

public class PieceCanNotMoveException extends CustomException {
    public PieceCanNotMoveException(ErrorCode errorCode) {
        super(errorCode);
    }
}
