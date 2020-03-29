package chess.domain.chessGame;

import java.util.Arrays;

public enum CommandStatus {

	START("start", 1),
	END("end", 1),
	MOVE("move", 3),
	STATUS("status", 2);

	private final String command;
	private final int requireArgumentsSize;

	CommandStatus(String command, int requireArgumentsSize) {
		this.command = command;
		this.requireArgumentsSize = requireArgumentsSize;
	}

	public static CommandStatus of(String command) {
		return Arrays.stream(values())
			.filter(commandStatus -> commandStatus.command.equals(command))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유효한 명령어가 아닙니다."));
	}

	public boolean isStartCommandStatus() {
		return this.equals(START);
	}

	public boolean isMoveCommandStatus() {
		return this.equals(MOVE);
	}

	public boolean isStatusCommandStatus() {
		return this.equals(STATUS);
	}

	public boolean isEndCommandStatus() {
		return this.equals(END);
	}

	public boolean isCorrectArgumentsSize(int argumentsSize) {
		return this.requireArgumentsSize == argumentsSize;
	}

}
