package chess.domain.command;

import static chess.domain.command.CommandType.*;

import chess.domain.position.Position;

public class Command {
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
		String[] commands = input.split(" ");
		CommandType commandType = CommandType.of(commands[0]);

		if (MOVE.equals(commandType)) {
			Position targetPosition = Position.of(commands[1]);
			Position destination = Position.of(commands[2]);
			return new Command(commandType, targetPosition, destination);
		}
		return new Command(commandType);
	}

	public boolean isNotEndOfGame() {
		return commandType != END && commandType != STATUS;
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

	public Position getTargetPosition() {
		return targetPosition;
	}

	public Position getDestination() {
		return destination;
	}
}
