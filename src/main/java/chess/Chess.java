package chess;

import chess.view.InputView;
import chess.view.OutputView;

public class Chess {

	public void start() {
		OutputView.printStartMessage();
		Command command = inputCommand();
	}

	private Command inputCommand() {
		try {
			return Command.from(InputView.input());
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
			return inputCommand();
		}
	}
}
