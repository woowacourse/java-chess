package chess.domain;

import java.util.List;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceInitializer;

public class Board {

	private final List<Piece> pieces;

	public Board() {
		this.pieces = PieceInitializer.generate();
	}

	public String findSymbolAt(int row, int column) {
		return pieces.stream()
			.filter(piece -> piece.isSamePosition(row, column))
			.findAny()
			.orElseThrow(IllegalArgumentException::new)
			.getSymbol();
	}
}
