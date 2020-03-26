package chess.domain;

import java.util.Objects;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public enum Command {
	MOVE("move"),
	STATUS("status"),
	END("end");

	private String command;

	Command(String command) {
		this.command = command;
	}

	public static Command of(String[] input) {
		validate(input);
		return Command.valueOf(input[0]);
	}

	private static void validate(String[] input) {
		validateLength(input);
		String command = input[0];
		validateNullAndEmpty(command);
		validateStartOrEnd(command);
	}

	private static void validateNullAndEmpty(String command) {
		if (Objects.isNull(command) || command.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값은 들어올 수 없습니다.");
		}
	}

	private static void validateLength(String[] command) {
		if (command.length != 1) {
			throw new IllegalArgumentException("입력값이 Command 길이가 1이 아닙니다.");
		}
	}

	private static void validateStartOrEnd(String input) {
		if (!input.equals(MOVE.command) && !input.equals(END.command) && !input.equals(STATUS.command)) {
			throw new IllegalArgumentException("명령은 move, status, end만 들어올 수 있습니다.");
		}
	}

	public boolean isEnd() {
		return this == END;
	}
}
