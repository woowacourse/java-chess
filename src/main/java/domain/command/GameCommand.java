package domain.command;

import static domain.command.InvalidCommandException.*;

import java.util.Arrays;

public enum GameCommand {
	START("start"),
	END("end");

	private String command;

	GameCommand(String command) {
		this.command = command;
	}

	public static GameCommand ofGameCommand(String inputCommand) {
		return Arrays.stream(GameCommand.values())
			.filter(gameGameCommand -> gameGameCommand.getGameCommand().equals(inputCommand))
			.findFirst()
			.orElseThrow(() -> new InvalidCommandException(INVALID_GAME_COMMAND));
	}

	public String getGameCommand() {
		return command;
	}
}
