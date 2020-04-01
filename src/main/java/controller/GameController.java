package controller;

import domain.command.GameCommand;
import domain.command.InvalidCommandException;
import view.InputView;
import view.OutputView;

public class GameController {
	public void run() {
		OutputView.printChessGameStart();
		GameCommand gameCommand = inputGameCommand();
		if (GameCommand.START == gameCommand) {
			new ChessController();
		}
	}

	private GameCommand inputGameCommand() {
		try {
			return GameCommand.ofGameCommand(InputView.inputCommand());
		} catch (InvalidCommandException e) {
			OutputView.printErrorMessage(e);
			return inputGameCommand();
		}
	}
}