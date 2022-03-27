package chess.domain.command;

import java.util.List;

import chess.domain.position.Position;

public interface Command {

	String START = "start";
	String END = "end";
	String MOVE = "move";
	String STATUS = "status";
	String INVALID_COMMAND = "유효한 커맨드가 아닙니다.";
	int MOVE_COMMAND_SIZE = 3;
	int NOT_MOVE_COMMAND_SIZE = 1;

	boolean isStart();

	boolean isMove();

	boolean isStatus();

	Position getFromPosition();

	Position getToPosition();

	static Command from(List<String> input) {
		if (!isNotMove(input) && !isMove(input)) {
			throw new IllegalArgumentException(INVALID_COMMAND);
		}
		return convertCommand(input);
	}

	private static boolean isNotMove(List<String> command) {
		String headCommand = command.get(0);
		return (headCommand.equals(START)
			|| headCommand.equals(END)
			|| headCommand.equals(STATUS)) && command.size() == NOT_MOVE_COMMAND_SIZE;
	}

	private static boolean isMove(List<String> command) {
		return command.get(0).equals(MOVE) && command.size() == MOVE_COMMAND_SIZE;
	}

	private static Command convertCommand(List<String> input) {
		if (input.get(0).equals(START)) {
			return new Start();
		}
		if (input.get(0).equals(END)) {
			return new End();
		}
		if (input.get(0).equals(STATUS)) {
			return new Status();
		}
		return new Move(input.get(1), input.get(2));
	}
}
