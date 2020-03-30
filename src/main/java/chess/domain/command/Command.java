package chess.domain.command;

import java.util.Objects;

import chess.domain.board.Position;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class Command {
	private String[] command;

	public Command(String[] input) {
		validate(input);
		this.command = input;
	}

	private void validate(String[] input) {
		String command = input[0];
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
		return Position.of(command[1]);
	}

	public Position getMoveTarget() {
		return Position.of(command[2]);
	}

	public boolean isEnd() {
		return "end".equalsIgnoreCase(command[0]);
	}

	public boolean isStatus() {
		return "status".equalsIgnoreCase(command[0]);
	}

	public boolean isMove() {
		return "move".equalsIgnoreCase(command[0]);
	}
}
