package chess.domain.command;

import java.util.Objects;

import chess.domain.board.Position;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class Command {
	public static final int COMMAND_INDEX = 0;
	private static final int SOURCE_POSITION_INDEX = 1;
	private static final int TARGET_POSITION_INDEX = 2;
	private static final int MOVE_COMMAND_SIZE = 3;
	public static final int BASIC_COMMAND_SIZE = 1;
	public static final String DELIMITER = " ";
	public static final int LIMIT = -1;

	private String[] command;

	public Command(String input) {
		String[] tempCommand = splitCommand(input);
		validate(tempCommand);
		command = tempCommand;
	}

	private String[] splitCommand(String input) {
		return input.split(DELIMITER, LIMIT);
	}

	private void validate(String[] input) {
		validateLength(input);
		validateNullAndEmpty(input[COMMAND_INDEX]);
		validateStartOrEndOrMove(input[COMMAND_INDEX]);
	}

	private static void validateLength(String[] input) {
		if (input.length != BASIC_COMMAND_SIZE && input.length != MOVE_COMMAND_SIZE) {
			throw new IllegalArgumentException("명령문의 길이가 1 또는 3이어야합니다.");
		}
	}

	private void validateNullAndEmpty(String command) {
		if (Objects.isNull(command) || command.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값은 들어올 수 없습니다.");
		}
	}

	private void validateStartOrEndOrMove(String input) {
		if (!CommandType.MOVE.isSame(input) && !CommandType.END.isSame(input) && !CommandType.STATUS.isSame(input)) {
			throw new IllegalArgumentException("명령은 move, status, end만 들어올 수 있습니다.");
		}
	}

	public Position getSourceCommand() {
		return Position.of(command[SOURCE_POSITION_INDEX]);
	}

	public Position getTargetCommand() {
		return Position.of(command[TARGET_POSITION_INDEX]);
	}

	public boolean isEnd() {
		return CommandType.END.isSame(command[COMMAND_INDEX]);
	}

	public boolean isStatus() {
		return CommandType.STATUS.isSame(command[COMMAND_INDEX]);
	}

	public boolean isMove() {
		return CommandType.MOVE.isSame(command[COMMAND_INDEX]);
	}

}
