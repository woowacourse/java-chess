package chess.domain.command;

import java.util.Objects;

public enum FirstCommand {
	START("start"),
	END("end");

	private final String command;

	FirstCommand(String command) {
		this.command = command;
	}

	private static void validate(String[] input) {
		validateNullAndEmpty(input);
		validateLength(input);
		validateStartOrEnd(input[0]);
	}

	public static FirstCommand of(String[] input) {
		validate(input);
		return findFirstCommand(input[0]);
	}

	private static void validateLength(String[] command) {
		if (command.length != 1) {
			throw new IllegalArgumentException("첫 명령어는 길이가 1이여야 합니다.");
		}
	}

	private static void validateNullAndEmpty(String[] command) {
		if (Objects.isNull(command) || command.length < 1) {
			throw new IllegalArgumentException("null이나 빈 값은 들어올 수 없습니다.");
		}
	}

	private static void validateStartOrEnd(String input) {
		if (!input.equals(START.command) && !input.equals(END.command)) {
			throw new IllegalArgumentException("첫 명령은 start나 end만 들어올 수 있습니다.");
		}
	}

	private static FirstCommand findFirstCommand(String command) {
		if (START.command.equalsIgnoreCase(command)) {
			return START;
		}
		return END;
	}

	public boolean isEnd() {
		return this == END;
	}
}
