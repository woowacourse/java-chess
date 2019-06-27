package chess.exception;

public class UnmovableException extends RuntimeException {
	public UnmovableException() {
		super("말을 움직일 수 없습니다.");
	}
}
