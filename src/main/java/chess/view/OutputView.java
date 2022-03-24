package chess.view;

import chess.domain.Board;
import chess.domain.Position;

public class OutputView {

	public void displayChessBoard(Board board) {
		for (int i = 8; i > 0; i--) {
			displayLine(board, i);
			System.out.println();
		}
	}

	private void displayLine(Board board, int i) {
		for (int j = 1; j <= 8; j++) {
			displaySymbol(board, i, j);
		}
	}

	private void displaySymbol(Board board, int i, int j) {
		try {
			System.out.print(board.findPiece(new Position(i, j)).getSymbol());
		} catch (IllegalArgumentException exception) {
			System.out.print("ꕤ");
		}
	}
}
