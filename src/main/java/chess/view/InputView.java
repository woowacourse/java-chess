package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import chess.domain.state.Command;
import chess.dto.RequestDto;
import chess.exception.IllegalCommandException;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);
	private static final String BLANK = " ";
	private static final int COMMAND_INDEX = 0;
	private static final int CORRECT_SIZE = 3;
	private static final int SOURCE_INPUT = 1;
	private static final int TARGET_INPUT = 3;
	private static final int START_OR_END = 1;
	private static final int MOVE = 3;

	public static RequestDto inputRequest() {
		System.out.println("\n명령어를 입력해주세요");
		String userInput = scanner.nextLine();
		validateInput(userInput);

		List<String> splitInput = Arrays.asList(userInput.split(BLANK));
		Command command = convertToCommand(splitInput.get(COMMAND_INDEX));

		if (splitInput.size() == CORRECT_SIZE) {
			return RequestDto.of(command, splitInput.subList(SOURCE_INPUT, TARGET_INPUT));
		}
		return RequestDto.of(command);
	}

	private static void validateInput(String userInput) {
		int length = userInput.split(BLANK).length;
		if (length != START_OR_END && length != MOVE) {
			throw new IllegalArgumentException("올바르지 않은 명령어 입니다.");
		}
	}

	private static Command convertToCommand(String userInput) {
		return Arrays.stream(Command.values())
			.filter(command -> command.isSameCommand(userInput.toUpperCase()))
			.findAny()
			.orElseThrow(() -> new IllegalCommandException("올바르지 않은 명령어 입니다."));
	}
}
