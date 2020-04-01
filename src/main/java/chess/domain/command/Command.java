package chess.domain.command;

import static chess.domain.command.CommandType.END;
import static chess.domain.command.CommandType.MOVE;
import static chess.domain.command.CommandType.START;
import static chess.domain.command.CommandType.STATUS;

import chess.domain.coordinates.Coordinates;

public class Command {
	private static final String DELIMITER = " ";
	private static final int FIRST_COMMAND_INDEX = 0;
	private static final int COMMAND_ROW_INDEX = 1;
	private static final int COMMAND_COLUMN_INDEX = 2;

	private CommandType commandType;
	private Coordinates targetCoordinates;
	private Coordinates destination;

	private Command(CommandType commandType) {
		this.commandType = commandType;
		this.targetCoordinates = null;
		this.destination = null;
	}

	private Command(CommandType commandType, Coordinates targetCoordinates, Coordinates destination) {
		this.commandType = commandType;
		this.targetCoordinates = targetCoordinates;
		this.destination = destination;
	}

	public static Command of(String input) {
		String[] commands = input.split(DELIMITER);
		CommandType commandType = CommandType.of(commands[FIRST_COMMAND_INDEX]);

		if (MOVE.equals(commandType)) {
			Coordinates targetCoordinates = Coordinates.of(commands[COMMAND_ROW_INDEX]);
			Coordinates destination = Coordinates.of(commands[COMMAND_COLUMN_INDEX]);
			return new Command(commandType, targetCoordinates, destination);
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

	public boolean isEnd() {
		return commandType == END;
	}

	public Coordinates getTargetCoordinates() {
		return targetCoordinates;
	}

	public Coordinates getDestination() {
		return destination;
	}
}
