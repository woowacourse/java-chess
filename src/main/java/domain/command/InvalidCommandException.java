package domain.command;

public class InvalidCommandException extends IllegalArgumentException {
	public static final String INVALID_GAME_COMMAND = "start 또는 end를 입력해주세요.";
	public static final String INVALID_CHESS_COMMAND = "move, status, end 중 하나를 입력해주세요.";

	public InvalidCommandException(String s) {
		super(s);
	}
}