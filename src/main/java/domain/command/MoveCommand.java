package domain.command;

import java.util.ArrayList;
import java.util.List;

import domain.piece.position.Position;

public class MoveCommand {
	private static final int SOURCE_POSITION = 0;
	private static final int TARGET_POSITION = 1;
	private static final String DELIMITER = " ";

	private List<Position> positions;

	public MoveCommand(String command) {
		positions = convert(split(command));
	}

	private String[] split(String inputCommand) {
		String[] commands = inputCommand.split(DELIMITER);
		boolean isWrongArgumentCount = Command.MOVE_ARGUMENT_COUNT != commands.length;
		if (isWrongArgumentCount) {
			throw new InvalidCommandException(InvalidCommandException.INVALID_MOVE_COMMAND);
		}
		return commands;
	}

	private List<Position> convert(String[] commands) {
		positions = new ArrayList<>();
		int countOfMoveArgument = commands.length;
		for (int i = 1; i < countOfMoveArgument; i++) {
			positions.add(Position.of(commands[i]));
		}
		return positions;
	}

	public Position getSourcePosition() {
		return positions.get(SOURCE_POSITION);
	}

	public Position getTargetPosition() {
		return positions.get(TARGET_POSITION);
	}
}
