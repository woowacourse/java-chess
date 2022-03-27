package chess.view;

import java.util.Scanner;

import chess.domain.command.Command;

public class InputView {

	private static final String INVALID_INPUT = "유효하지 않은 입력입니다.";

	private final Scanner scanner = new Scanner(System.in);

	public Command askCommand() {
		String input = scanner.nextLine();
		validateInput(input);
		return CommandConverter.from(input);
	}

	private void validateInput(String input) {
		if (input.isEmpty() || input.isBlank()) {
			throw new IllegalArgumentException(INVALID_INPUT);
		}
	}
}
