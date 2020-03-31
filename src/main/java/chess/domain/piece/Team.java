package chess.domain.piece;

public enum Team {
	BLACK, WHITE, NONE;

	public boolean isNotBlank() {
		return this != NONE;
	}

	public boolean isBlack() {
		return this == BLACK;
	}

	public boolean isWhite() {
		return this == WHITE;
	}

	public Team getOppositeTeam() {
		if (this.isBlack()) {
			return WHITE;
		}
		return BLACK;
	}
}
