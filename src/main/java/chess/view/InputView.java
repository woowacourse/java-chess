package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import chess.dto.RequestDto;
import chess.exception.IllegalCommandException;
import chess.state.Command;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static RequestDto inputRequest() {
		System.out.println("\n명령어를 입력해주세요");
		String userInput = scanner.nextLine();
		validateInput(userInput);
		List<String> splitInput = Arrays.asList(userInput.split(" "));
		Command command = convertToCommand(splitInput.get(0));

		if (splitInput.size() == 3) {
			return RequestDto.of(command, splitInput.subList(1, 3));
		}
		return RequestDto.of(command);
	}

	private static void validateInput(String userInput) {
		int length = userInput.split(" ").length;
		if (length != 1 && length != 3) {
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
