package chess.view;

import java.util.Scanner;

public class InputView {

	private static Scanner scanner = new Scanner(System.in);

	private InputView() {
	}

	public static Command inputCommand() {
		try {
			String input = scanner.nextLine();
			return Command.of(input);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return inputCommand();
		}
	}
}
