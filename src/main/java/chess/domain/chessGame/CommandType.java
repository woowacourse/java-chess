package chess.domain.chessGame;

import java.util.Arrays;

public enum CommandType {

	START("start", 1),
	END("end", 1),
	MOVE("move", 3),
	STATUS("status", 2);

	private final String command;
	private final int requireArgumentsSize;

	CommandType(String command, int requireArgumentsSize) {
		this.command = command;
		this.requireArgumentsSize = requireArgumentsSize;
	}

	public static CommandType of(String command) {
		return Arrays.stream(values())
			.filter(commandType -> commandType.command.equals(command))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유효한 명령어가 아닙니다."));
	}

	public boolean isStartCommandType() {
		return this.equals(START);
	}

	public boolean isMoveCommandType() {
		return this.equals(MOVE);
	}

	public boolean isStatusCommandType() {
		return this.equals(STATUS);
	}

	public boolean isEndCommandType() {
		return this.equals(END);
	}

	public boolean isCorrectArgumentsSize(int argumentsSize) {
		return this.requireArgumentsSize == argumentsSize;
	}

}
