package domain.exception;

public class InvalidDestinationPointException extends RuntimeException {
    public InvalidDestinationPointException() {
        super("장기말이 이동할 수 있는 경로가 아닙니다.");
    }
}
