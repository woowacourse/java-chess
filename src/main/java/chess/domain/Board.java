package chess.domain;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceInitializer;

public class Board {

	private final Map<Position, Piece> pieces;

	public Board() {
		this.pieces = PieceInitializer.generate();
	}

	public Piece findPiece(Position position) {
		return pieces.computeIfAbsent(position, key -> {
			throw new IllegalArgumentException();
		});
	}
}
