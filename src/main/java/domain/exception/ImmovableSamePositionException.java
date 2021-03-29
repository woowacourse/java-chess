package domain.exception;

public class ImmovableSamePositionException extends RuntimeException {
    public ImmovableSamePositionException() {
        super("동일한 위치로 이동할 수 없습니다.");
    }
}
