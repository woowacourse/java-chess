package chess.controller;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

	private final InputView inputView;
	private final OutputView outputView;
	
	private boolean endSystem = false;

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
		List<String> commandInput;
		Command command;
		try {
			commandInput = inputView.inputCommand();
			command = Command.from(commandInput.get(0));
			executeCommand(chessGame, command, commandInput);
			if (isEndSystem()) {
				return;
			}
			play(chessGame);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			play(chessGame);
		}
	}

	private boolean isEndSystem() {
		return endSystem;
	}

	private void executeCommand(final ChessGame chessGame, final Command command, final List<String> commandInput) {
		if (command == Command.START) {
			executeStartCommand(chessGame);
		}
		if (command == Command.MOVE) {
			executeMoveCommand(chessGame, commandInput);
		}
		if (command == Command.END) {
			executeEndCommand(chessGame);
		}
		if (command == Command.STATUS) {
			executeStatusCommand(chessGame);
		}
	}

	private void executeStartCommand(final ChessGame chessGame) {
		chessGame.start();
		outputView.printBoard(chessGame.getBoard().getValue());
	}

	private void executeMoveCommand(final ChessGame chessGame, final List<String> commandInput) {
		Position sourcePosition = Position.from(commandInput.get(1));
		Position targetPosition = Position.from(commandInput.get(2));

		chessGame.move(sourcePosition, targetPosition);

		outputView.printBoard(chessGame.getBoard().getValue());
	}

	private void executeEndCommand(final ChessGame chessGame) {
		changeEndSystemAtNotRunning(chessGame);
		chessGame.end();
		printResult(chessGame);
	}

	private void changeEndSystemAtNotRunning(final ChessGame chessGame) {
		if (!chessGame.isRunning()) {
			endSystem = true;
		}
	}

	private void executeStatusCommand(final ChessGame chessGame) {
		outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
	}

	private void printResult(final ChessGame chessGame) {
		outputView.printFinishMessage();
		outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
		outputView.printWinner(chessGame.findWinner());
	}
}
