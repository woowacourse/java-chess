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
public class ConsoleController {
	private static final ConsoleController GAME_CONTROLLER = new ConsoleController();
	private Pieces pieces;
	private GameManager gameManager;

	public static ConsoleController getInstance() {
		return GAME_CONTROLLER;
	}

	public void run() {
		start();
		running();
		end();
	}

	public void start() {
		OutputView.printGameInstruction();
		FirstCommand command = new FirstCommand(InputView.inputCommand());

		if (command.isEnd()) {
			end();
		}
	}

	public void running() {
		pieces = new Pieces(Pieces.initPieces());
		gameManager = new GameManager(pieces);

		Command command;

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

	private void move(Command command, GameManager gameManager) {
		try {
			gameManager.moveFromTo(command.getSourceCommand(), command.getTargetCommand());
		} catch (IllegalArgumentException e) {
			OutputView.printException(e);
		}
	}

	private void end() {
		OutputView.printGameEnd();
		System.exit(0);
	}

	private Command readCommand() {
		try {
			return new Command(InputView.inputCommand());
		} catch (IllegalArgumentException e) {
			OutputView.printException(e);
			return readCommand();
		}
	}

	public Pieces getPieces() {
		return this.pieces;
	}
}
