package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import chess.domain.Position;

public class InputView {

	private static final String INVALID_INPUT = "start와 end만 가능합니다.";
	private static final String PREFIX_MOVE = "이동하려면 MOVE를 입력해야 합니다.";

	private static final String START = "start";
	private static final String END = "end";
	private static final String MOVE = "move";

	private static final Scanner scanner = new Scanner(System.in);
	public static final String INVALID_COMMAND = "move a1 a2의 형식으로 입력하세요";

	public String askCommand() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");

		String input = scanner.nextLine();
		validateInput(input);
		return input;
	}

	private void validateInput(String input) {
		if (!input.equalsIgnoreCase(START) && !input.equalsIgnoreCase(END)) {
			throw new IllegalArgumentException(INVALID_INPUT);
		}
	}

	public List<String> askMoveCommand() {
		String input = scanner.nextLine();
		validateTwoPositions(input);
		return Arrays.asList(input.split(" ")).subList(1, 3);
	}

	private void validateTwoPositions(String input) {
		if (input.startsWith(MOVE)) {
			throw new IllegalArgumentException(PREFIX_MOVE);
		}
		List<String> splitInput = Arrays.asList(input.split(" "));
		if (splitInput.size() != 3) {
			throw new IllegalArgumentException(INVALID_COMMAND);
		}
		if (splitInput.get(1).length() != 2 || splitInput.get(2).length() != 2) {
			throw new IllegalArgumentException(INVALID_COMMAND);
		}
	}
}
