package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final String MOVE_COMMAND_INPUT_EXCEPTION = "이동 명령을 형식에 맞게 입력하세요.";
	private static final String EMPTY_INPUT_EXCEPTION = "명령을 입력하세요.";
	private static final String COMMAND_DELIMITER = " ";
	private static final Scanner SCANNER = new Scanner(System.in);

	public List<String> inputCommand() {
		final String input = SCANNER.nextLine();
		try {
			return splitCommand(input);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputCommand();
		}
	}

	private List<String> splitCommand(final String input) {
		List<String> command;
		validateEmpty(input);
		command = Arrays.asList(SCANNER.nextLine().split(COMMAND_DELIMITER));
		if (isMoveCommand(command)) {
			validateMoveCommandSize(command);
		}
		return command;
	}

	private boolean isMoveCommand(final List<String> command) {
		return "move".equals(command.get(0));
	}

	private void validateEmpty(final String input) {
		if (input.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_INPUT_EXCEPTION);
		}
	}

	private void validateMoveCommandSize(final List<String> command) {
		if (command.size() != 3) {
			throw new IllegalArgumentException(MOVE_COMMAND_INPUT_EXCEPTION);
		}
	}
}
