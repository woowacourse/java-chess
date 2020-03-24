package chess;

public enum Command {
	START("start"),
	END("end");

	private final String command;

	Command(String command) {
		this.command = command;
	}
}
