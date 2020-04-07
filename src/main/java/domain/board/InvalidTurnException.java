package domain.board;

public class InvalidTurnException extends IllegalArgumentException {
	public static final String INVALID_TURN = "현재 턴과 움직일 말의 팀이 일치하지 않습니다.";

	public InvalidTurnException(String s) {
		super(s);
	}
}
