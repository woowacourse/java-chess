package chess.controller;

import chess.domain.Command;
import chess.domain.FirstCommand;
import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.piece.BlackPiecesFactory;
import chess.domain.piece.Pieces;
import chess.domain.piece.WhitePiecesFactory;
import chess.view.InputView;
import chess.view.OutputView;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class Controller {
	private static Game game;

	public static void run() {
		start();
		running();
		end();
	}

	private static void start() {
		OutputView.printGameInstruction();

		FirstCommand command = readFirstCommand();
		if (command.isEnd()) {
			end();
		}
		init();
	}

	private static FirstCommand readFirstCommand() {
		try {
			return FirstCommand.of(InputView.inputCommand());
		} catch (IllegalArgumentException e) {
			OutputView.printException(e);
			return readFirstCommand();
		}
	}

	private static void init() {
		game = new Game(new Pieces(WhitePiecesFactory.create(), BlackPiecesFactory.create()), Board.getInstance());
		OutputView.printChessBoard(game.getBoard());
	}

	private static void running() {
		Command command;

		do {
			command = readCommand();
			if (command.isMove()) {
				movePiece(command);
			}
			if (command.isStatus()) {
				// Todo: status
			}
		} while (!command.isEnd() || kingDie());
	}

	private static void movePiece(Command command) {
		try {
			game.movePieceFromTo(command.getSource(), command.getTarget());
			OutputView.printChessBoard(game.getBoard());
		} catch (UnsupportedOperationException e) {
			OutputView.printException(e);
		}
	}

	private static Command readCommand() {
		try {
			return Command.of(InputView.inputCommand());
		} catch (IllegalArgumentException e) {
			OutputView.printException(e);
			return readCommand();
		}
	}

	private static boolean kingDie() {
		return false;
	}

	private static void end() {
		OutputView.printGameEnd();
		System.exit(0);
	}
}
