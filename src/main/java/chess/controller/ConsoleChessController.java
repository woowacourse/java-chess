package chess.controller;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.state.Ready;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessController {
	public void run() {
		OutputView.printGameIntro();
		Game game = new Game(new Ready(new Board()));
		play(game);
		OutputView.printWinner(game.findWinner());
	}

	private void play(Game game) {
		while (game.isNotEnd()) {
			inputCommand(game);
		}
	}

	private void inputCommand(Game game) {
		try {
			Command.action(game, InputView.requestCommand());
		} catch (RuntimeException e) {
			OutputView.printExceptionMessage(e.getMessage());
			inputCommand(game);
		}
	}
}
