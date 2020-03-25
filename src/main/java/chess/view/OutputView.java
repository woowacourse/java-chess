package chess.view;

import chess.domain.Board;
import chess.util.ConsoleOutputRenderer;

public class OutputView {
	private OutputView() {
	}

	public static void printBoard(Board board) {
		System.out.println(ConsoleOutputRenderer.renderBoard(board));
	}
}
