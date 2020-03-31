package chess.contoller.command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class UserCommand {
	private static final String DELIMITER = " ";
	private static final int MOVE_OPTION_NEED = 2;

	private Command command;
	private Queue<String> options;

	private UserCommand(Command command, Queue<String> options) {
		this.command = command;
		this.options = options;
	}

	public static UserCommand of(String string) {
		validateEmpty(string);
		Queue<String> inputs = new LinkedList<>(Arrays.asList(string.split(DELIMITER)));
		return new UserCommand(Command.of(inputs.poll()), inputs);
	}

	private static void validateEmpty(String string) {
		if (Objects.isNull(string) || string.isEmpty()) {
			throw new IllegalArgumentException("빈 값을 입력하지 마세요.");
		}
	}

	public Command getCommand() {
		return command;
	}

	public String pollOption() {
		return options.poll();
	}

	public void validateOptionCount() {
		if (options.size() < MOVE_OPTION_NEED) {
			throw new IllegalArgumentException("옵션의 개수가 부족합니다.");
		}
	}
}
