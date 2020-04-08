package chess.domain.board;

import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Piece;
import chess.util.PieceNameConverter;

public class Cell {
	private final Coordinates coordinates;
	private final Piece piece;

	public Cell(Coordinates coordinates, Piece piece) {
		this.coordinates = coordinates;
		this.piece = piece;
	}

	public String getCoordinatesName() {
		return coordinates.getName();
	}

	public String getPieceName() {
		return PieceNameConverter.toPieceType(piece);
	}
}
