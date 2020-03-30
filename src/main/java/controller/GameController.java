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
		Command command = inputGameCommand();
		if (Command.START == command) {
			new ChessController();
		}
	}

	private Command inputGameCommand() {
		try {
			return Command.ofGameCommand(InputView.inputCommand());
		} catch (InvalidCommandException e) {
			OutputView.printErrorMessage(e);
			return inputGameCommand();
		}
	}
}