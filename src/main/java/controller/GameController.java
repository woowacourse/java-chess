package controller;

import domain.command.Command;
import view.InputView;
import view.OutputView;

public class GameController {
	public GameController() {
		run();
	}

	private void run() {
		OutputView.printChessGameStart();
		Command command = Command.ofGameControlCommand(InputView.inputCommand());
		if (Command.START == command) {
			new ChessController();
		}
	}
}