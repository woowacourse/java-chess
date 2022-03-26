package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

	private static final String INVALID_INPUT = "유효하지 않은 입력입니다.";

	private static final String START = "start";
	private static final String END = "end";
	private static final String MOVE = "move";

	private static final Scanner scanner = new Scanner(System.in);

	public List<String> askCommand() {
		String input = scanner.nextLine();
		validateInput(input);
		return Arrays.asList(input.split(" "));
	}

	private void validateInput(String input) {
		List<String> command = Arrays.asList(input.split(" "));
		if (command.isEmpty()) {
			throw new IllegalArgumentException();
		}

		if (!isStartOrEnd(command) && !isMove(command)) {
			throw new IllegalArgumentException(INVALID_INPUT);
		}
	}

	private boolean isStartOrEnd(List<String> command) {
		String headCommand = command.get(0);
		return (headCommand.equals(START) || headCommand.equals(END)) && command.size() == 1;
	}

	private boolean isMove(List<String> command) {
		return command.get(0).equals(MOVE) && command.size() == 3;
	}
}
