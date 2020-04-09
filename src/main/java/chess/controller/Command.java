package chess.controller;

import static java.util.regex.Pattern.*;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

import chess.domain.game.Game;
import chess.domain.position.Position;
import chess.domain.result.Result;
import chess.view.OutputView;

public enum Command {
	START(compile("start"), (game, command) -> {
		game.start();
		OutputView.printBoard(game.getBoard());
	}),
	STATUS(compile("status"), (game, command) -> {
		Result status = game.status();
		OutputView.printStatus(status.getStatus());
	}),
	MOVE(compile("move \\w\\d \\w\\d"), (game, command) -> {
		String[] s = command.split(" ");
		game.move(Position.of(s[1]), Position.of(s[2]));
		OutputView.printBoard(game.getBoard());
	}),
	END(compile("end"), (game, command) -> {
		game.end();
	});

	private static final String NOT_EXIST_COMMAND_EXCEPTION_MESSAGE = "존재하지 않는 명령어입니다.";

	private final Pattern commdanRegex;
	private final BiConsumer<Game, String> gameCommand;

	Command(Pattern commdanRegex, BiConsumer<Game, String> gameCommand) {
		this.commdanRegex = commdanRegex;
		this.gameCommand = gameCommand;
	}

	public static void action(Game game, String command) {
		Command comm = of(command);
		comm.gameCommand.accept(game, command);
	}

	private static Command of(String command) {
		return Arrays.stream(values())
			.filter(value -> isMatches(command, value))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_COMMAND_EXCEPTION_MESSAGE));
	}

	private static boolean isMatches(String command, Command value) {
		return value.commdanRegex.matcher(command).matches();
	}
}
