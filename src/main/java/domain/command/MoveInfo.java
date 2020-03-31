package domain.command;

import java.util.ArrayList;
import java.util.List;

import domain.piece.position.Position;
import view.InputView;

public class MoveInfo {
	private List<Position> positions;

	public MoveInfo(String command) {
		positions = convert(split(command));
	}

	private String[] split(String inputCommand) {
		String[] commands = inputCommand.split(InputView.DELIMITER);
		if (Command.MOVE_ARGUMENT_SIZE != commands.length) {
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
		return positions.get(InputView.SOURCE_POSITION);
	}

	public Position getTargetPosition() {
		return positions.get(InputView.TARGET_POSITION);
	}
}
