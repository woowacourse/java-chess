package chess;

import static chess.view.OutputView.*;

import java.sql.SQLException;

import chess.domain.GameManager;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
	public static void main(String[] args) throws SQLException {
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

	public static void startGame() throws SQLException {
		Board board = BoardFactory.create();
		GameManager gameManager = new GameManager(board);

		resumeGame(gameManager);
	}

	private static Command resumeGame(GameManager gameManager) throws SQLException {
		Command command;
		do {
			printBoard(gameManager.getBoard());
			command = inputCommand();
			if (!command.isMove()) {
				break;
			}
			gameManager.move(command.getTargetPosition(), command.getDestination());
		} while (command.isMove() && gameManager.isKingAlive());
		printResult(gameManager, command);
		return command;
	}

	private static void printResult(GameManager gameManager, Command command) throws SQLException {
		if (command.isStatus()) {
			OutputView.printResultScore(gameManager.calculateEachScore());
		} else {
			OutputView.printWinner(gameManager.getCurrentTurn().reverse());
		}
	}
}
