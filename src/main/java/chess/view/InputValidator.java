package chess.view;

import java.util.Arrays;
import java.util.List;

public class InputValidator {

	private static final String DELIMITER = " ";
	private static final String START = "start";
	private static final String END = "end";
	private static final String MOVE = "move";
	private static final String STATUS = "status";
	private static final String COMMAND_MATCH_ERROR = "명령어는 start, end, move, status 로 입력해야 합니다.";
	private static final String WRONG_LENGTH_ERROR = "움직일 기물의 위치는 [알파벳 숫지] 2글자로 입력해야 합니다.";
	private static final int COMMAND_INDEX = 0;
	private static final int SOURCE_INDEX = 1;
	private static final int TARGET_INDEX = 2;
	private static final int POSITION_LENGTH = 2;
	private static final int MOVE_COMMAND_LENGTH = 3;

	public static void validateCommand(final String answer) {
		List<String> input = Arrays.asList(answer.split(DELIMITER));
		String command = input.get(COMMAND_INDEX);
		if (command.equalsIgnoreCase(START) || command.equalsIgnoreCase(END) || command.equalsIgnoreCase(STATUS)) {
			return;
		}
		if (command.equalsIgnoreCase(MOVE) && input.size() == MOVE_COMMAND_LENGTH) {
			validateLength(input);
			return;
		}

		throw new IllegalArgumentException(COMMAND_MATCH_ERROR);
	}

	private static void validateLength(final List<String> input) {
		String sourcePosition = input.get(SOURCE_INDEX);
		String targetPosition = input.get(TARGET_INDEX);
		if (sourcePosition.length() != POSITION_LENGTH || targetPosition.length() != POSITION_LENGTH) {
			throw new IllegalArgumentException(WRONG_LENGTH_ERROR);
		}
	}
}
