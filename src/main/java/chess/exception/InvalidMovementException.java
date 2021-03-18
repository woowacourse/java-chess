package chess.exception;

public class InvalidMovementException extends ChessException {
    // 기물 이동 룰에 어긋남 - 기물
    // 기물이 아님 - 블랭크
    // 장애물이 있음 - 보드
    public InvalidMovementException(String message) {
        super(message);
    }
}
