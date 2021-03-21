package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	public static final String STATUS = "status";
	private static final String MOVE = "move";
	private static final Scanner SCANNER = new Scanner(System.in);
	private static final String START = "start";
	private static final String END = "end";

	private InputView() {
	}

	public static boolean isStart() {
		String input = SCANNER.nextLine();
		validate(input);
		return START.equals(input);
	}

	private static void validate(String input) {
		if (!START.equals(input) && !END.equals(input)) {
			throw new IllegalArgumentException();
		}
	}

	public static List<String> moveOrStatus() {
		String input = SCANNER.nextLine();
		List<String> splitInput = Arrays.stream(input.split("\\s"))
				.map(String::trim)
				.collect(Collectors.toList());
		validateStatusOrMove(splitInput.get(0));
		return splitInput;
	}

	public static void validateStatusOrMove(String input) {
		if (!STATUS.equals(input) && !MOVE.equals(input)) {
			throw new IllegalArgumentException();
		}
	}
}
