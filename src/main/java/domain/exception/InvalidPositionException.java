package domain.exception;

public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException() {
        super("잘못된 포지션입니다.");
    }
}
