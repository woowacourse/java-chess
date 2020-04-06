package chess.domain.command;

import static chess.domain.command.Command.*;

import java.util.Objects;

public class FirstCommand {
	private final String command;

	public FirstCommand(String input) {
		String[] tempCommand = splitCommand(input);
		validate(tempCommand);
		this.command = tempCommand[COMMAND_INDEX];
	}

	private String[] splitCommand(String input) {
		validateNullAndEmpty(input);
		return input.split(DELIMITER, LIMIT);
	}

	private void validate(String[] input) {
		validateLength(input);
		String command = input[COMMAND_INDEX];
		validateNullAndEmpty(command);
		validateStartOrEnd(command);
	}

	private void validateNullAndEmpty(String command) {
		if (Objects.isNull(command) || command.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값은 들어올 수 없습니다.");
		}
	}

	private void validateLength(String[] command) {
		if (command.length != BASIC_COMMAND_SIZE) {
			throw new IllegalArgumentException("처음 입력값은 Command 길이가 1이어야 합니다.");
		}
	}

	private void validateStartOrEnd(String input) {
		if (!CommandType.START.isSame(input) && !CommandType.END.isSame(input)) {
			throw new IllegalArgumentException("첫 명령은 start, end만 들어올 수 있습니다.");
		}
	}

	public boolean isEnd() {
		return CommandType.END.isSame(command);
	}
}
