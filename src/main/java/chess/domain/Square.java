package chess.domain;

import chess.domain.piece.Piece;

public class Square {

	private Piece piece;

	public Square(final Piece piece) {
		this.piece = piece;
	}

	public boolean isBlack() {
		return piece.isBlack();
	}

	public boolean isWhite() {
		return piece.isWhite();
	}

	public boolean hasPiece() {
		return !piece.isEmpty();
	}
}
