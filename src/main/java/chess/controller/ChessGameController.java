package chess.controller;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

	private final InputView inputView;
	private final OutputView outputView;

	public ChessGameController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		outputView.printStartMessage();
		final ChessGame chessGame = new ChessGame();
		
		play(chessGame);
	}

	private void play(final ChessGame chessGame) {
		try {
			final List<String> commandInput = inputView.inputCommand();
			final Command command = Command.from(commandInput.get(0));
			executeCommandWithoutEnd(chessGame, command, commandInput);
			if (isEndSystem(chessGame, command)) {
				return;
			}
			play(chessGame);
		} catch (Exception e) {
			outputView.printException(e.getMessage());
			play(chessGame);
		}
	}

	private void executeCommandWithoutEnd(final ChessGame chessGame, final Command command, final List<String> commandInput) {
		if (command == Command.START) {
			executeStartCommand(chessGame);
		}
		if (command == Command.MOVE) {
			executeMoveCommand(chessGame, commandInput);
		}
		if (command == Command.STATUS) {
			executeStatusCommand(chessGame);
		}
	}

	private void executeStartCommand(final ChessGame chessGame) {
		chessGame.start();
		outputView.printBoard(chessGame.getBoard().getPiecesByPosition());
	}

	private void executeMoveCommand(final ChessGame chessGame, final List<String> commandInput) {
		Position sourcePosition = Position.from(commandInput.get(1));
		Position targetPosition = Position.from(commandInput.get(2));

		chessGame.move(sourcePosition, targetPosition);
		if (!chessGame.isRunning()) {
			printResult(chessGame);
			return;
		}

		outputView.printBoard(chessGame.getBoard().getPiecesByPosition());
	}

	private boolean isEndSystem(final ChessGame chessGame, final Command command) {
		if (command == Command.END) {
			return executeEndCommand(chessGame);
		}
		return false;
	}

	private boolean executeEndCommand(final ChessGame chessGame) {
		if (!chessGame.isRunning()) {
			return true;
		}
		chessGame.end();
		printResult(chessGame);
		return false;
	}

	private void executeStatusCommand(final ChessGame chessGame) {
		outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
	}

	private void printResult(final ChessGame chessGame) {
		outputView.printResult(chessGame.statusOfWhite(), chessGame.statusOfBlack(), chessGame.findWinner());
	}
}
