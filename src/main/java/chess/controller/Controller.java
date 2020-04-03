package chess.controller;

import chess.domain.GameManager;
import chess.domain.command.Command;
import chess.domain.command.FirstCommand;
import chess.domain.piece.Pieces;
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

	private static void start() {
		OutputView.printGameInstruction();
		FirstCommand command = new FirstCommand(InputView.inputCommand());

		if (command.isEnd()) {
			end();
		}
	}

	private static void running() {
		Command command;
		Pieces pieces = new Pieces(Pieces.initPieces());
		GameManager gameManager = new GameManager(pieces);

		OutputView.printChessBoard(gameManager);

		do {
			command = readCommand();
			if (command.isMove()) {
				move(command, gameManager);
			} else if (command.isStatus()) {
				OutputView.printStatus(gameManager);
			}
			OutputView.printChessBoard(gameManager);
		} while (!command.isEnd() || !gameManager.isKingDead());
		OutputView.printStatus(gameManager);
		end();
	}

	private static void move(Command command, GameManager gameManager) {
		try {
			gameManager.moveFromTo(command.getSourceCommand(), command.getTargetCommand());
		} catch (IllegalArgumentException e) {
			OutputView.printException(e);
		}
	}

	private static void end() {
		OutputView.printGameEnd();
		System.exit(0);
	}

	private static Command readCommand() {
		try {
			return new Command(InputView.inputCommand());
		} catch (IllegalArgumentException e) {
			OutputView.printException(e);
			return readCommand();
		}
	}
}
