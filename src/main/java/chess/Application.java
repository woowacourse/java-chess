package chess;

import static chess.view.OutputView.*;

import chess.domain.GameManager;
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
		GameManager gameManager = new GameManager();
		gameManager.resetGame();

		resumeGame(gameManager);
	}

	private static Command resumeGame(GameManager gameManager) {
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

	private static void printResult(GameManager gameManager, Command command) {
		if (command.isStatus()) {
			OutputView.printResultScore(gameManager.calculateEachScore());
		} else {
			OutputView.printWinner(gameManager.getCurrentTurn().reverse());
		}
	}
}
