package chess.controller;

import chess.domain.Command;
import chess.domain.FirstCommand;
import chess.domain.board.Position;
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
		Command command;
		Position source = Position.of("b2");
		Position target = Position.of("b2");

		do {
			command = readCommand();
		} while (!command.isEnd() || kingDie());
	}

	private static boolean kingDie() {
		return false;
	}

	private static void start() {
		OutputView.printGameInstruction();
		FirstCommand command = FirstCommand.of(InputView.inputCommand());

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
			return Command.of(InputView.inputCommand());
		} catch (IllegalArgumentException e) {
			OutputView.printException(e);
			return readCommand();
		}
	}
}
