package domain;

public class PieceWrapper {
	private final Piece piece;

	public PieceWrapper(Piece piece) {
		this.piece = piece;
	}

	boolean isOn(Position position) {
		return piece.isOn(position);
	}

	public Team getTeam() {
		return piece.getTeam();
	}
}
