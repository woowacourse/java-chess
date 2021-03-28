package domain.exception;

public class InvalidMenuException extends RuntimeException {
    public InvalidMenuException() {
        super("메뉴를 잘못 입력하셨습니다.");
    }
}
