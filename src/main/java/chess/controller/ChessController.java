package chess.controller;

import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

	public void run() {
		ChessGame chessGame = new ChessGame();
		OutputView.printInitialMessage();
		Command lastCommand;
		do {
			lastCommand = runInputCommand(chessGame);
			if (chessGame.isGameDone()) {
				OutputView.printWinner(chessGame.getFinalWinner().toString());
				return;
			}
		} while (lastCommand != Command.END);
	}

	private Command runInputCommand(ChessGame chessGame) {
		return ExceptionHandler.RetryIfThrowsException(() -> {
			Command command = readCommand();
			return command.run(chessGame);
		});
	}

	private Command readCommand() {
		return ExceptionHandler.RetryIfThrowsException(() ->
			InputRenderer.toCommand(InputView.readCommand()));
	}
}
