package chess.view;

import java.util.Arrays;

public enum Command {

	START("start"),
	END("end"),
	MOVE("move"),
	STATUS("status");

	private final String command;

	Command(String command) {
		this.command = command;
	}

	public static boolean isEnd(final String requestGameStart) {
		return Arrays.stream(values())
				.anyMatch(command -> command == END && command.getCommand().equalsIgnoreCase(requestGameStart));
	}

	public static boolean isStart(final String requestGameStart) {
		return Arrays.stream(values())
				.anyMatch(command -> command == START && command.getCommand().equalsIgnoreCase(requestGameStart));
	}

	public static boolean isMove(final String request) {
		return Arrays.stream(values())
				.anyMatch(command -> command == MOVE && command.getCommand().equalsIgnoreCase(request));
	}

	public static boolean isStatus(final String request) {
		return Arrays.stream(values())
				.anyMatch(command -> command == STATUS && command.getCommand().equalsIgnoreCase(request));
	}

	public String getCommand() {
		return command;
	}
}
