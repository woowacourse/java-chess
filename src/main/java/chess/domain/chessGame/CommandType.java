package chess.domain.chessGame;

import java.util.Arrays;
import java.util.Objects;

public enum CommandType {

	START("start", 1),
	END("end", 1),
	MOVE("move", 3),
	STATUS("status", 2);

	private final String command;
	private final int requireArgumentsSize;

	CommandType(final String command, final int requireArgumentsSize) {
		this.command = command;
		this.requireArgumentsSize = requireArgumentsSize;
	}

	public static CommandType of(final String command) {
		Objects.requireNonNull(command, "명령이 null입니다.");

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

	public boolean isCorrectArgumentsSize(final int argumentsSize) {
		return this.requireArgumentsSize == argumentsSize;
	}

}
