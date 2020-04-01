package domain.command;

import java.util.Arrays;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public enum ChessCommand {
	MOVE("move"),
	STATUS("status"),
	END("end");

	private String command;

	ChessCommand(String command) {
		this.command = command;
	}

	public static ChessCommand ofChessCommand(String inputCommand) {
		return Arrays.stream(ChessCommand.values())
			.filter(c -> c.getCommand().equals(inputCommand))
			.findFirst()
			.orElseThrow(() -> new InvalidCommandException(InvalidCommandException.INVALID_CHESS_COMMAND));
	}

	public String getCommand() {
		return command;
	}
}
