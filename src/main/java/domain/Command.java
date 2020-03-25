package domain;

import static domain.InvalidCommandException.*;

import java.util.Arrays;

public enum Command {
	START("start"),
	END("end");

	private String command;

	Command(String command) {
		this.command = command;
	}

	public static Command of(String inputCommand) {
		return Arrays.stream(Command.values())
			.filter(command -> command.getCommand().equals(inputCommand))
			.findFirst()
			.orElseThrow(() -> new InvalidCommandException(INVALID_GAME_CONTROL_COMMAND));
	}

	public String getCommand() {
		return command;
	}
}
