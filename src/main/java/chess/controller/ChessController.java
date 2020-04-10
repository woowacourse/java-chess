package chess.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Consumer;

import chess.ChessGame;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	private ChessGame game;
	private Map<String, Consumer<StringTokenizer>> commands;

	public ChessController(ChessGame game) {
		this.game = game;
		this.commands = new HashMap<String, Consumer<StringTokenizer>>() {{
			put("start", tokenizer -> {
				game.start();
				OutputView.printGameStart();
				OutputView.printBoard(game.board());
			});
			put("end", tokenizer -> game.end());
			put("move", tokenizer -> {
				game.move(Position.of(tokenizer.nextToken()),
					Position.of(tokenizer.nextToken()));
				OutputView.printBoard(game.board());
			});
			put("status", tokenizer -> OutputView.printStatus(game.status()));
		}};
	}

	public void command(String input) {
		StringTokenizer tokenizer = new StringTokenizer(input, " ");
		commands.get(tokenizer.nextToken()).accept(tokenizer);
	}

	public void run() {
		OutputView.printGameStart();
		while (!game.isEnd()) {
			command(InputView.inputRequest());
		}
	}
}

