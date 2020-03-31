package domain.command;

public class InvalidCommandException extends IllegalArgumentException {
	public static final String INVALID_MOVE_COMMAND = "move b2 b3 형식으로 입력해주세요.";
	public static final String INVALID_COMMAND_TYPE = "명령어를 잘못 입력하였습니다. ";

	public InvalidCommandException(String s) {
		super(s);
	}
}