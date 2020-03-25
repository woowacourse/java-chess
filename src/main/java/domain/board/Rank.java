package domain.board;

import java.util.List;

import domain.piece.Piece;

public class Rank {
	private List<Piece> pieces;

	public Rank(List<Piece> pieces) {
		this.pieces = pieces;
	}
}
