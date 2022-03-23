package chess.view;

import java.util.Arrays;

public enum Command {
	START("start"),
	END("end");

	private final String command;

	Command(String command) {
		this.command = command;
	}

	public static boolean isEnd(final String requestGameStart) {
		return requestGameStart.equalsIgnoreCase("end") && Arrays.stream(values())
				.anyMatch(command -> command.getCommand().equalsIgnoreCase(requestGameStart));
	}

	public String getCommand() {
		return command;
	}
}
