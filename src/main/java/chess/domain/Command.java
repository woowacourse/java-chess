package chess.domain;

import java.util.Objects;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class Command {
	private String command;

	public Command(String command) {
		validate(command);
		this.command = command;
	}

	private void validate(String command) {
		validateNullAndEmpty(command);
		validateStart(command);
	}

	private void validateStart(String command) {
		if (command.equalsIgnoreCase("start")) {
			throw new IllegalArgumentException("start는 처음 한 번만 입력할 수 있습니다.");
		}
	}

	private void validateNullAndEmpty(String command) {
		if (Objects.isNull(command) || command.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값이 올 수 없습니다.");
		}
	}

	public boolean isEnd() {
		return false;
	}
}
