package domain.exception;

public class AlreadyFinishedException extends RuntimeException {
    public AlreadyFinishedException() {
        super("게임이 이미 종료되었습니다.");
    }
}
