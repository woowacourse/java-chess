package chess.view;

public enum Command {

	START("start"),
	END("end"),
	MOVE("move"),
	STATUS("status");

	private final String command;

	Command(String command) {
		this.command = command;
	}

	public static boolean isEnd(final String request) {
		return request.equalsIgnoreCase(END.command);
	}

	public static boolean isStart(final String request) {
		return request.equalsIgnoreCase(START.command);
	}

	public static boolean isMove(final String request) {
		return request.equalsIgnoreCase(MOVE.command);
	}

	public static boolean isStatus(final String request) {
		return request.equalsIgnoreCase(STATUS.command);
	}
}
