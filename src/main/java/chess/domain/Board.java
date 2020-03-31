package chess.domain;

import java.util.List;

import chess.domain.piece.Piece;

public class Board {
	private List<Piece> pieces;

	public Board(List<Piece> pieces) {
		this.pieces = pieces;
	}
}
