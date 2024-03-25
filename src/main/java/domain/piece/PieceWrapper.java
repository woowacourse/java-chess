package domain.piece;

import domain.Position;
import domain.Team;

public class PieceWrapper {
	private final Piece piece;

	public PieceWrapper(Piece piece) {
		this.piece = piece;
	}

	public boolean isOn(Position position) {
		return piece.isOn(position);
	}

	public int getColumn() {
		return piece.getColumn();
	}

	public int getRow() {
		return piece.getRow();
	}

	public Team getTeam() {
		return piece.getTeam();
	}

	public PieceType getPieceType() {
		return piece.getPieceType();
	}
}
