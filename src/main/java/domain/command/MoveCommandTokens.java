package domain.command;

import domain.command.exceptions.MoveCommandTokensException;

public class MoveCommandTokens {
	private static final int MOVE_COMMAND_SIZE = 3;
	private static final int SOURCE_INDEX = 1;
	private static final int DESTINATION_INDEX = 2;

	private final String source;
	private final String destination;

	private MoveCommandTokens(String source, String destination) {
		this.source = source;
		this.destination = destination;
	}

	public static MoveCommandTokens of(String moveCommand) {
		String[] strings = moveCommand.split(" ");

		if (strings.length != MOVE_COMMAND_SIZE) {
			throw new MoveCommandTokensException("move command 인자는 2개여야 합니다.");
		}

		return new MoveCommandTokens(strings[SOURCE_INDEX], strings[DESTINATION_INDEX]);
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}
}
