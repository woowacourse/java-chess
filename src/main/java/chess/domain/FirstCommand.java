package chess.domain;

import java.util.Objects;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class FirstCommand {
	private String command;

	public FirstCommand(String[] command) {
		validate(command);
		this.command = command[0];
	}

	private void validate(String[] command) {
		valdiateNullAndEmpty(command);
		validateStartOrEnd(command[0]);
	}

	private void valdiateNullAndEmpty(String[] command) {
		if (Objects.isNull(command) || command.length < 1) {
			throw new IllegalArgumentException("null이나 빈 값은 들어올 수 없습니다.");
		}
	}

	private void validateStartOrEnd(String s) {
		boolean isNotStart = !s.equalsIgnoreCase("start");
		boolean isNotEnd = !s.equalsIgnoreCase("end");

		if (isNotStart && isNotEnd) {
			throw new IllegalArgumentException("첫 명령은 start, end만 들어올 수 있습니다.");
		}
	}

	public boolean isEnd() {
		return command.equalsIgnoreCase("end");
	}
}
