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

		playGame(chessGame);
	}

	private void playGame(ChessGame chessGame) {
		List<String> commandInput = inputView.inputCommand();
		try {
			Command command = Command.from(commandInput.get(0));
			checkStartCommand(chessGame, command);
			checkMoveCommand(chessGame, command, commandInput);
			if (checkEndSystemForEndCommand(chessGame, command))
				return;
			checkStatusCommand(chessGame, command);
			playGame(chessGame);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			playGame(chessGame);
		}
	}

	private void checkStartCommand(ChessGame chessGame, Command command) {
		if (command == Command.START) {
			executeStartCommand(chessGame);
		}
	}

	private void executeStartCommand(ChessGame chessGame) {
		chessGame.start();
		outputView.printBoard(chessGame.getBoard().getValue());
	}

	private void checkMoveCommand(ChessGame chessGame, Command command, List<String> commandInput) {
		if (command == Command.MOVE) {
			executeMoveCommand(chessGame, commandInput);
			if (!chessGame.isRunning()) {
				printResult(chessGame);
			}
		}
	}

	private void executeMoveCommand(ChessGame chessGame, List<String> commandInput) {
		Position sourcePosition = Position.from(commandInput.get(1));
		Position targetPosition = Position.from(commandInput.get(2));

		chessGame.move(sourcePosition, targetPosition);

		outputView.printBoard(chessGame.getBoard().getValue());
	}

	private boolean checkEndSystemForEndCommand(ChessGame chessGame, Command command) {
		if (command == Command.END) {
			if (!chessGame.isRunning()) {
				return true;
			}
			chessGame.end();
			printResult(chessGame);
		}
		return false;
	}

	private void checkStatusCommand(ChessGame chessGame, Command command) {
		if (command == Command.STATUS) {
			outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
		}
	}

	private void printResult(ChessGame chessGame) {
		outputView.printFinishMessage();
		outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
		outputView.printWinner(chessGame.findWinner());
	}
}
