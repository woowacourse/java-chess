package chess;

import static chess.view.OutputView.*;

import chess.domain.GameManager;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
	public static void main(String[] args) {
		printStartMessage();
		Command command = inputCommand();
		if (command.isStart()) {
			startGame();
		}
	}

	public static Command inputCommand() {
		String command = InputView.inputAsCommand();
		return Command.of(command);
	}

	public static void startGame() {
		Board board = BoardFactory.create();
		GameManager gameManager = new GameManager(board);

		Command command = resumeGame(board, gameManager);
	}

	private static Command resumeGame(Board board, GameManager gameManager) {
		Command command;
		do {
			printBoard(board);
			command = inputCommand();
			if (!command.isMove()) {
				break;
			}
			gameManager.move(command);
		} while (command.isMove() && gameManager.isKingAlive());
		printResult(gameManager, command);
		return command;
	}

	private static void printResult(GameManager gameManager, Command command) {
		if (command.isStatus()) {
			OutputView.printResultScore(gameManager.calculateEachScore());
		} else {
			OutputView.printWinner(gameManager.getCurrentTurn().reverse());
		}
	}
}
