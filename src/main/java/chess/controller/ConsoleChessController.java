package chess.controller;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessController {
	public void run() {
		OutputView.printGameIntro();
		Board board = new Board();
		GameState gameState = new Ready(board);
		Game game = new Game(gameState);

		while (game.isNotEnd()) {
			doCommand(game);
		}
		OutputView.printWinner(game.findWinner());
	}

	private void doCommand(Game game) {
		try {
			Command.action(game, InputView.requestCommand());
		} catch (RuntimeException e) {
			OutputView.printErrorMessage(e.getMessage());
			doCommand(game);
		}
	}
}
