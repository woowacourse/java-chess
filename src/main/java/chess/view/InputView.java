package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.Command;
import chess.domain.position.Square;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);
	private static final String SPLIT_REGEX = " ";
	private static final String ERROR_MESSAGE_LACK_INFORMATION = "[ERROR] 부족해잉~ 더줘잉~";
	private static final String ERROR_MESSAGE_POSITION_FORMAT = "[ERROR] 위치의 포맷을 지켜서 입력하세요.";
	private static final String ERROR_MESSAGE_TMI = "[ERROR] 투 머치 인포메이션~ㅋ";

	private static final int COMMAND_INDEX = 0;
	private static final int SOURCE_INDEX = 1;
	private static final int TARGET_INDEX = 2;
	private static final int COMMAND_MOVE_FORMAT_SIZE = 3;
	private static final int POSITION_SIZE = 2;
	private static final int COMMAND_NOT_MOVE_FORMAT_SIZE = 1;

	public static Map.Entry<Command, List<Square>> requireCommand() {
		try {
			List<String> commands = getCommands();
			Command command = Command.from(commands.get(COMMAND_INDEX));
			validateCommandFormatSize(command, commands);
			if (command == Command.MOVE) {
				return Map.entry(command, getSourceAndTarget(commands));
			}
			return Map.entry(command, List.of());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return requireCommand();
		}
	}

	private static List<String> getCommands() {
		String answer = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
		return Arrays.stream(answer.split(SPLIT_REGEX))
			.filter(cmd -> !cmd.isBlank())
			.collect(Collectors.toList());
	}

	private static List<Square> getSourceAndTarget(List<String> commands) {
		List<Square> squares;
		String source = commands.get(SOURCE_INDEX);
		String target = commands.get(TARGET_INDEX);
		validatePositionFormat(source, target);
		squares = List.of(new Square(commands.get(SOURCE_INDEX)), new Square(commands.get(
			TARGET_INDEX)));
		return squares;
	}

	private static void validateCommandFormatSize(Command command, List<String> commands) {
		if (Command.MOVE == command) {
			validateInformationCount(commands, COMMAND_MOVE_FORMAT_SIZE);
			return;
		}

		validateInformationCount(commands, COMMAND_NOT_MOVE_FORMAT_SIZE);
	}

	private static void validateInformationCount(List<String> commands, int size) {
		if (commands.size() < size) {
			throw new IllegalArgumentException(ERROR_MESSAGE_LACK_INFORMATION);
		}

		if (commands.size() > size) {
			throw new IllegalArgumentException(ERROR_MESSAGE_TMI);
		}
	}

	private static void validatePositionFormat(String source, String target) {
		if (source.length() != POSITION_SIZE || target.length() != POSITION_SIZE) {
			throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_FORMAT);
		}
	}
}
