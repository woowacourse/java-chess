package domain.exception;

public class PieceEmptyException extends RuntimeException {
    public PieceEmptyException() {
        super("선택된 위치는 빈칸입니다.");
    }
}
