package chess;

import java.util.ArrayList;
import java.util.List;

import chess.exception.SamePositionException;
import chess.piece.Piece;

public class ChessBoard {
	private final List<Piece> pieces;

	public ChessBoard() {
		pieces = new ArrayList<>();
	}

	public void addPiece(Piece newPiece) {
		for (Piece piece : pieces) {
			if (piece.isSamePosition(newPiece)) {
				throw new SamePositionException();
			}
		}
		pieces.add(newPiece);
	}
}
