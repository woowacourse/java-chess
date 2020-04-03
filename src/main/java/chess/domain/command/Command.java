package chess.domain.command;

import static chess.domain.command.CommandType.*;

import chess.domain.position.Position;

public class Command {
	private static final String DELIMITER = " ";
	private static final int FIRST_COMMAND_INDEX = 0;
	private static final int COMMAND_ROW_INDEX = 1;
	private static final int COMMAND_COLUMN_INDEX = 2;

	private CommandType commandType;
	private Position targetPosition;
	private Position destination;

	private Command(CommandType commandType) {
		this.commandType = commandType;
		this.targetPosition = null;
		this.destination = null;
	}

	private Command(CommandType commandType, Position targetPosition, Position destination) {
		this.commandType = commandType;
		this.targetPosition = targetPosition;
		this.destination = destination;
	}

	public static Command of(String input) {
		String[] commands = input.split(DELIMITER);
		CommandType commandType = CommandType.of(commands[FIRST_COMMAND_INDEX]);

		if (MOVE.equals(commandType)) {
			Position targetPosition = Position.of(commands[COMMAND_ROW_INDEX]);
			Position destination = Position.of(commands[COMMAND_COLUMN_INDEX]);
			return new Command(commandType, targetPosition, destination);
		}
		return new Command(commandType);
	}

	public boolean isStart() {
		return commandType == START;
	}

	public boolean isMove() {
		return commandType == MOVE;
	}

	public boolean isStatus() {
		return commandType == STATUS;
	}

	public boolean isResume() {
		return commandType == RESUME;
	}

	public Position getTargetPosition() {
		return targetPosition;
	}

	public Position getDestination() {
		return destination;
	}
}
