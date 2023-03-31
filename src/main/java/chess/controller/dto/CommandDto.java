package chess.controller.dto;

import chess.controller.Command;

public class CommandDto {

	private final Command command;

	private CommandDto(final Command command) {
		this.command = command;
	}

	public static CommandDto from(final String string) {
		Command command = Command.from(string.toUpperCase());
		return new CommandDto(command);
	}

	public Command getCommand() {
		return command;
	}
}
