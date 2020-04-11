package chess.exception;

public class PieceMoveFailedException extends RuntimeException {
	private static final String MOVE_FAILED_EXCEPTION = "움직일 수 없습니다.";

	public PieceMoveFailedException() {
		super(MOVE_FAILED_EXCEPTION);
	}

	public PieceMoveFailedException(String message) {
		super(message);
	}
}
