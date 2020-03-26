package chess.command;

import java.util.Arrays;

public enum CommandType {
	START("start", true),
	MOVE("move", true),
	END("end", false);

	String command;
	boolean state;

	CommandType(String command, boolean state) {
		this.command = command;
		this.state = state;
	}

	public static CommandType of(String command) {
		return Arrays.stream(values())
			.filter(commandType -> commandType.command.equals(command))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드를 입력했습니다."));
	}

	public boolean isGaming() {
		return this.state;
	}
}
