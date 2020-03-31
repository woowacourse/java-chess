package controller;

import domain.command.Command;
import domain.command.InvalidCommandException;
import view.InputView;
import view.OutputView;

public class GameController {
	public GameController() {
		run();
	}

	private void run() {
		OutputView.printChessGameStart();
		Command command = Command.of(InputView.inputCommand());
		if (command.isEnd()) {
			OutputView.printGameIsEnd();
			System.exit(0);
		}

		if (command.isStart()) {
			new ChessController();
		}

		throw new InvalidCommandException(InvalidCommandException.INVALID_COMMAND_TYPE);
	}
}