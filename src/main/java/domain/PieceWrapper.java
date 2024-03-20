package domain;

class PieceWrapper {
	private final Piece piece;

	PieceWrapper(Piece piece) {
		this.piece = piece;
	}

	boolean isOn(Position position) {
		return piece.isOn(position);
	}

	Team getTeam() {
		return piece.getTeam();
	}
}
