package chess;

import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printErrorMessage;
import static chess.view.OutputView.printScore;
import static chess.view.OutputView.printStartMessage;
import static chess.view.OutputView.printWinner;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.command.Command;
import chess.view.InputView;

public class Application {
	private static final int EXIT_SUCCESSFULLY = 0;

	public static void main(String[] args) {
		try {
			run();
		} catch (Exception exception) {
			printErrorMessage(exception.getMessage());
		}
	}

	private static void run() {
		Command command;
		do {
			printStartMessage();
			command = inputCommand();
			startGame(command);
		} while (!command.isEnd());
	}

	public static void startGame(Command command) {
		if (command.isStart()) {
			resumeGame();
			return;
		}
		if (!command.isEnd()) {
			throw new IllegalArgumentException("처음에는 게임 시작 또는 종료만 할 수 있습니다.");
		}
	}

	private static void resumeGame() {
		Board board = BoardFactory.createNewGame();
		ChessGame chessGame = new ChessGame(board);
		while (!chessGame.isEndOfGame()) {
			printBoard(chessGame.getBoard());
			Command command = inputCommand();
			executeOperation(chessGame, command);
		}
		printWinner(chessGame.getEnemyColor());
	}

	private static void executeOperation(ChessGame chessGame, Command command) {
		if (command.isMove()) {
			chessGame.move(command.getTargetCoordinates(), command.getDestination());
		}
		if (command.isStatus()) {
			printScore(chessGame.calculateScore());
		}
		if (command.isEnd()) {
			System.exit(EXIT_SUCCESSFULLY);
		}
	}

	public static Command inputCommand() {
		String command = InputView.inputAsCommand();
		return Command.of(command);
	}
}
