package chess.controller;

import chess.controller.dto.CommandDto;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

	public void run(final ChessService chessService) {
		if (!chessService.isGameStarted()) {
			OutputView.printInitialMessage();
		}
		runInputCommand(chessService);
		if (chessService.isGameDone()) {
			OutputView.printWinner(chessService.getFinalWinner().toString());
		}
	}

	private void runInputCommand(final ChessService service) {
		ExceptionHandler.RetryIfThrowsException(() -> {
			Command command = readCommand();
			return command.run(service);
		});
	}

	private Command readCommand() {
		return ExceptionHandler.RetryIfThrowsException(() -> {
			CommandDto commandDto = InputView.readCommand();
			return commandDto.getCommand();
		});
	}
}
