package domain;

public class Command {
	private static final String START = "start";
	private static final String END = "end";
	private static final String MOVE = "move";
	private final String command;
	private final String[] options;

	public Command(String command, String... options) {
		validate(command, options);
		this.command = command;
		this.options = options;
	}

	private void validate(String command, String[] options) {
		if (!command.equals(START) && !command.equals(END) && !command.equals(MOVE)) {
			throw new IllegalArgumentException("잘못된 명령어입니다.");
		}

		if (command.equals(MOVE) && options.length != 2) {
			throw new IllegalArgumentException("잘못된 명령어입니다.");
		}
	}
}
