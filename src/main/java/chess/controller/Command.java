package chess.controller;

import java.util.Arrays;
import java.util.function.BiConsumer;

import chess.domain.game.Game;
import chess.domain.position.Position;
import chess.view.OutputView;

public enum Command {
	START("start", (game, command) -> {
		game.start();
		OutputView.printBoard(game.getBoard());
	}),
	STATUS("status", (game, command) -> {
		game.status();
		System.out.println(game.status());
	}),
	MOVE("move \\w\\d \\w\\d", (game, command) -> {
		String[] s = command.split(" ");
		game.move(Position.of(s[1]), Position.of(s[2]));
		OutputView.printBoard(game.getBoard());
	}),
	END("end", (game, command) -> {
		game.end();
	});

	private static final String NOT_EXIST_COMMAND_EXCEPTION_MESSAGE = "존재하지 않는 명령어입니다.";

	private final String commandRegex;
	private final BiConsumer<Game, String> gameCommand;

	Command(String commandRegex, BiConsumer<Game, String> gameCommand) {
		this.commandRegex = commandRegex;
		this.gameCommand = gameCommand;
	}

	public static void action(Game game, String command) {
		Command comm = of(command);
		comm.gameCommand.accept(game, command);
	}

	private static Command of(String command) {
		return Arrays.stream(values())
			.filter(value -> command.matches(value.commandRegex))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_COMMAND_EXCEPTION_MESSAGE));
	}
}
