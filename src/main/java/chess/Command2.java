package chess;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum Command2 {
	START(Pattern.compile("start")),
	END(Pattern.compile("end")),
	MOVE(Pattern.compile("move \\w\\d \\w\\d")),
	STATUS(Pattern.compile("status"));

	private static final String NOT_EXIST_COMMAND_EXCEPTION_MESSAGE = "존재하지 않는 명령어입니다.";

	private final Pattern commandPattern;

	Command2(Pattern commandPattern) {
		this.commandPattern = commandPattern;
	}

	public static Command2 of(String command) {
		return Arrays.stream(values())
			.filter(value -> isMatches(command, value))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_COMMAND_EXCEPTION_MESSAGE));
	}

	private static boolean isMatches(String command, Command2 value) {
		Pattern commandPattern = value.commandPattern;
		return commandPattern.matcher(command).matches();
	}

	public boolean isStart() {
		return this == START;
	}

	public boolean isEnd() {
		return this == END;
	}

	public boolean isMove() {
		return this == MOVE;
	}

	public boolean isStatus() {
		return this == STATUS;
	}
}
