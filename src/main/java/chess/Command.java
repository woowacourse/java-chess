package chess;

import java.util.Arrays;

public enum Command {
	START("start"),
	MOVE("move"),
	END("end"),
	STATUS("status");

	private static final String ERROR_MESSAGE_COMMAND = "[ERROR] 그런 명령어는 없는뎅...ㅎ;;";

	private final String name;

	Command(String name) {
		this.name = name;
	}

	public static Command from(String name) {
		return Arrays.stream(Command.values())
			.filter(command -> command.name.equals(name))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE_COMMAND));
	}
}
