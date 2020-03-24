package chess.domain;

import java.util.List;

import chess.domain.piece.Piece;

public class Pieces {
	private final List<Piece> pieces;

	public Pieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public Piece findByPosition(Position position) {
		return pieces.stream()
			.filter(p -> p.isSamePosition(position))
			.findFirst()
			.orElse(null);
	}
}
