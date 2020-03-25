package controller;

import domain.Command;
import domain.board.Board;
import domain.board.BoardFactory;
import view.InputView;
import view.OutputView;

public class Controller {
	public Controller() {
		run();
	}

	private void run() {
		OutputView.printChessGameStart();
		Command command = Command.of(InputView.inputGameControlCommand());
		if (Command.START == command) {
			Board board = BoardFactory.create();
			OutputView.printChessBoard(board);
		}
		if (Command.END == command) {
			System.exit(0);
		}
	}
}
