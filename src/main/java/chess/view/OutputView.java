package chess.view;

import java.util.Optional;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Piece;

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
		Optional<Piece> nullablePiece = board.findPiece(new Position(i, j));
		if (nullablePiece.isPresent()) {
			System.out.println(nullablePiece.get().getSymbol());
			return;
		}
		System.out.println("ꕤ");
	}
}
