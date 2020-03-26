package chess;

import static chess.view.OutputView.*;

import chess.domain.Board;
import chess.domain.GameManager;
import chess.domain.command.Command;
import chess.domain.piece.BoardFactory;
import chess.view.InputView;

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

	public static Command startGame() {
		Board board = BoardFactory.create();
		GameManager gameManager = new GameManager(board);

		Command command;
		do {
			printBoard(board);
			command = inputCommand();
			if (!command.isMove()) {
				break;
			}
			gameManager.move(command);

		} while (command.isMove());
		// 게임 결과 도출

		return command;
	}
}
