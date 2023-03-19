package chess;

import chess.domain.Board;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {

	public static void main(String[] args) {
		Board board = Board.create();
		try {
			start(board);
			play(board);
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
		}
	}

	private static void start(final Board board) {
		String inputCommand = InputView.askStart();
		Command.ofStart(inputCommand);

		OutputView.printBoard(board);
	}

	private static void play(final Board board) {
		String inputCommand = InputView.askNext();
		Command command = Command.ofMoveOrEnd(inputCommand);
		if (command.isEnd()) {
			return;
		}
		if (command.isMove()) {
			board.move(command.getSource(), command.getTarget());
			OutputView.printBoard(board);
			play(board);
		}
	}
}
