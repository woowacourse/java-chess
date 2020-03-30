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
	public static final int SOURCE_POSITION_INDEX = 1;
	public static final int TARGET_POSITION_INDEX = 2;

	private String[] command;

	public Command(String[] input) {
		validate(input);
		this.command = input;
	}

	private void validate(String[] input) {
		String command = input[COMMAND_INDEX];
		validateNullAndEmpty(command);
		validateStartOrEndOrMove(command);
	}

	private void validateNullAndEmpty(String command) {
		if (Objects.isNull(command) || command.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값은 들어올 수 없습니다.");
		}
	}

	private void validateStartOrEndOrMove(String input) {
		if (!input.equalsIgnoreCase("move") && !input.equalsIgnoreCase("end") && !input.equalsIgnoreCase("status")) {
			throw new IllegalArgumentException("명령은 move, status, end만 들어올 수 있습니다.");
		}
	}

	public Position getMoveSource() {
		return Position.of(command[SOURCE_POSITION_INDEX]);
	}

	public Position getMoveTarget() {
		return Position.of(command[TARGET_POSITION_INDEX]);
	}

	public boolean isEnd() {
		return "end".equalsIgnoreCase(command[COMMAND_INDEX]);
	}

	public boolean isStatus() {
		return "status".equalsIgnoreCase(command[COMMAND_INDEX]);
	}

	public boolean isMove() {
		return "move".equalsIgnoreCase(command[COMMAND_INDEX]);
	}
}
