package chess.domain.board.exception;

public class InvalidTurnException extends RuntimeException {
	private static final String INVALID_TURN_MESSAGE = "상대방 턴 입니다.";

	public InvalidTurnException() {
		super(INVALID_TURN_MESSAGE);
	}
}
