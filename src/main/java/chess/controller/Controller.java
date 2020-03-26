package chess.controller;

import chess.domain.Command;
import chess.domain.FirstCommand;
import chess.view.InputView;
import chess.view.OutputView;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class Controller {
	public static void run() {
		start();
		running();
		end();
	}

	private static void running() {

	}

	private static void start() {
		OutputView.printGameInstruction();
		FirstCommand command = new FirstCommand(InputView.inputCommand());

		if (command.isEnd()) {
			end();
		}
	}

	private static void end() {
		OutputView.printGameEnd();
		System.exit(0);
	}

	private static Command readCommand() {
		try {
			String command = InputView.inputCommand();
			return new Command(command);
		} catch (IllegalArgumentException e) {
			OutputView.printException(e);
			return readCommand();
		}
	}
}
