package chess.controller;

import java.util.List;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.game.ChessGame;
import chess.function.Function;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

	public void run() {
		OutputView.printGameStart();
		ChessGame chessGame = new ChessGame();

		while (!chessGame.isFinished()) {
			List<String> commands = InputView.inputGameFunction();
			Function function = Function.from(commands.get(0));

			if (function == Function.START) {
				chessGame.start();
				OutputView.printBoard(chessGame.getValue());
			}

			if (function == Function.MOVE) {
				chessGame.move(Coordinate.of(commands.get(1)), Coordinate.of(commands.get(2)));
				OutputView.printBoard(chessGame.getValue());
			}

			if (function == Function.END) {
				chessGame.end();
			}
		}
	}
}
