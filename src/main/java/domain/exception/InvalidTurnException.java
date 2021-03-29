package domain.exception;

public class InvalidTurnException extends RuntimeException {
    public InvalidTurnException() {
        super("상대방의 차례입니다.");
    }
}
