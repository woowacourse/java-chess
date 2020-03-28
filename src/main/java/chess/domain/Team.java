package chess.domain;

public enum Team {
	WHITE,
	BLACK;

	public Team switchTeam() {
		if (this == WHITE) {
			return BLACK;
		}
		return WHITE;
	}
}
