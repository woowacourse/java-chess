package chess.controller;

import java.util.Arrays;
import java.util.List;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
	private static final String MOVE_COMMAND_INPUT_EXCEPTION = "이동 명령을 형식에 맞게 입력하세요.";
	private static final String COMMAND_DELIMITER = " ";

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
		final String commandInput = inputView.inputCommand();
		try {
			Command command = Command.from(commandInput);
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

	private void checkMoveCommand(ChessGame chessGame, Command command, String commandText) {
		if (command == Command.MOVE) {
			executeMoveCommand(chessGame, commandText);
			if (!chessGame.isRunning()) {
				printResult(chessGame);
			}
		}
	}

	private void executeMoveCommand(ChessGame chessGame, String commandText) {
		final List<String> commands = Arrays.asList(commandText.split(COMMAND_DELIMITER));

		validateMoveCommandInput(commands);

		Position sourcePosition = Position.from(commands.get(1));
		Position targetPosition = Position.from(commands.get(2));

		chessGame.move(sourcePosition, targetPosition);

		outputView.printBoard(chessGame.getBoard().getValue());
	}

	private void validateMoveCommandInput(List<String> commands) {
		if (commands.size() != 3) {
			throw new IllegalArgumentException(MOVE_COMMAND_INPUT_EXCEPTION);
		}
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
