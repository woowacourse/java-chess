package chess;

import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class Chess {
	private final Board board;

	public Chess(final Board board) {
		this.board = board;
	}

	public void start() {
		OutputView.printStartMessage();
		Command command = inputCommand();
		if (command == Command.END) {
			return;
		}
		OutputView.printBoard(board.getPieces());
	}

	private Command inputCommand() {
		try {
			return Command.from(InputView.input());
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
			return inputCommand();
		}
	}
}
