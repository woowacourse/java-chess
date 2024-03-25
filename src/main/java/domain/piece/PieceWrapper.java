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

	public Team getTeam() {
		return piece.getTeam();
	}
}
