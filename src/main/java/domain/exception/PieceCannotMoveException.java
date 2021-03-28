package domain.exception;

public class PieceCannotMoveException extends RuntimeException {
    public PieceCannotMoveException(String pieceName) {
        super(pieceName + "은 선택된 위치로 이동할 수 없습니다.");
    }
}
