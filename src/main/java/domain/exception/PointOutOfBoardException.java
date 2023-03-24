package domain.exception;

public class PointOutOfBoardException extends RuntimeException {
    public PointOutOfBoardException() {
        super("보드판을 넘어가는 위치 정보입니다.");
    }
}
