package chess.domain;

import chess.domain.piece.PieceFactory;

public class Board {
	private final Pieces pieces;

	public Board() {
		this.pieces = PieceFactory.getPieces();
	}

	public Pieces getPieces() {
		return pieces;
	}
}
