package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	public static final int COMMAND_INDEX = 0;
	private static final String INVALID_OPTION_ERROR = "옵션을 정확하게 입력해주세요.";
	private static final String SPACE = "\\s";

	private static final Scanner SCANNER = new Scanner(System.in);

	private InputView() {
	}

	public static boolean isStart() {
		try {
			String input = SCANNER.nextLine();
			validateStartOrEnd(input);
			return Option.START.getOption().equals(input);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return isStart();
		}
	}

	public static List<String> takeMoveOrStatusInput() {
		try {
			String input = SCANNER.nextLine();
			List<String> splitInput = Arrays.stream(input.split(SPACE))
					.map(String::trim)
					.collect(Collectors.toList());
			validateMoveOrStatus(splitInput.get(COMMAND_INDEX));
			return splitInput;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return takeMoveOrStatusInput();
		}
	}

	private static void validateStartOrEnd(String input) {
		if (Option.isStartOrEnd(input)) {
			throw new IllegalArgumentException(INVALID_OPTION_ERROR);
		}
	}

	private static void validateMoveOrStatus(String input) {
		if (Option.isMoveOrStatus(input)) {
			throw new IllegalArgumentException(INVALID_OPTION_ERROR);
		}
	}
}
