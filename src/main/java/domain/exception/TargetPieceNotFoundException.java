package domain.exception;

/**
 * 출발 좌표에 조작할 장기말이 존재하지 않을 때 발생하는 예외
* */
public class TargetPieceNotFoundException extends RuntimeException {
    public TargetPieceNotFoundException() {
        super("입력한 위치에는 이동 가능한 기물이 존재하지 않습니다.");
    }
}
