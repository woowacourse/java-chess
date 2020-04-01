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

	public boolean isSameTeam(Team team) {
		return this == team;
	}

	public boolean isNotSameTeam(Team team) {
		return !isSameTeam(team);
	}
}
