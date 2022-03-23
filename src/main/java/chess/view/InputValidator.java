package chess.view;

public class InputValidator {

	private static final String START = "start";
	private static final String END = "end";
	private static final String ERROR_MESSAGE = "[ERROR] ";
	private static final String COMMAND_MATCH_ERROR = ERROR_MESSAGE + "게임 시작 명령어는 start, end 로 입력해야 합니다.";

	public static void validateCommand(final String answer) {
		if (answer.equalsIgnoreCase(START) || answer.equalsIgnoreCase(END)) {
			return;
		}
		throw new IllegalArgumentException(COMMAND_MATCH_ERROR);
	}
}
