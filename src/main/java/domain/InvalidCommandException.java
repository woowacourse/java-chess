package domain;

public class InvalidCommandException extends IllegalArgumentException {
	public static final String INVALID_GAME_CONTROL_COMMAND = "start 또는 end를 입력해주세요.";

	public InvalidCommandException(String s) {
		super(s);
	}
}