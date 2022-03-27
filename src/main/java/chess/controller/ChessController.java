package chess.controller;

import java.util.List;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.game.ChessGame;
import chess.domain.game.ScoreCalculator;
import chess.function.Function;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

	public void run() {
		OutputView.printGameStart();
		ChessGame chessGame = new ChessGame();

		while (!chessGame.isFinished()) {
			play(chessGame);
		}
	}

	private void play(ChessGame chessGame) {
		try {
			List<String> commands = InputView.inputGameFunction();
			Function function = Function.from(commands.get(0));

			doFunction(chessGame, function, commands);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void doFunction(ChessGame chessGame, Function function, List<String> commands) {
		if (function == Function.START) {
			chessGame.start();
			OutputView.printBoard(chessGame.getValue());
		}

		if (function == Function.MOVE) {
			chessGame.move(Coordinate.of(commands.get(1)), Coordinate.of(commands.get(2)));
			OutputView.printBoard(chessGame.getValue());
		}

		if (function == Function.STATUS) {
			ScoreCalculator scoreCalculator = new ScoreCalculator(chessGame.getValue());
			OutputView.printStatus(scoreCalculator.createStatus());
		}

		if (function == Function.END) {
			chessGame.end();
		}
	}
}
