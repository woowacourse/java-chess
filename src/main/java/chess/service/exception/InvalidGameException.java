package chess.service.exception;

public class InvalidGameException extends RuntimeException {
	private static final String INVALID_GAME_MESSAGE = "존재하지 않는 게임입니다.";

	public InvalidGameException() {
		super(INVALID_GAME_MESSAGE);
	}
}
