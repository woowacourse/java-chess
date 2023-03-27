package chess.controller;

import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

	public void run() {
		final ChessService chessService = new ChessService();
		OutputView.printInitialMessage();
		Command lastCommand;
		do {
			lastCommand = runInputCommand(chessService);
			if (chessService.isGameDone()) {
				OutputView.printWinner(chessService.getFinalWinner().toString());
				return;
			}
		} while (lastCommand != Command.END);
	}

	private Command runInputCommand(final ChessService chessService) {
		return ExceptionHandler.RetryIfThrowsException(() -> {
			Command command = readCommand();
			return command.run(chessService);
		});
	}

	private Command readCommand() {
		return ExceptionHandler.RetryIfThrowsException(() ->
			InputRenderer.toCommand(InputView.readCommand()));
	}
}
