package chess.view;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Command {

	START("start"),
	END("end"),
	MOVE("move"),
	STATUS("status");

	private static final String NOT_FOUND_ERROR = "해당 명령어를 찾지 못했습니다.";

	private final String command;

	Command(String command) {
		this.command = command;
	}

	public static Command of(final String request) {
		return Arrays.stream(values())
				.filter(value -> value.command.equalsIgnoreCase(request))
				.findAny()
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ERROR));
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
