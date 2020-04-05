package domain.piece.team;

public enum Team {
	BLACK, WHITE;

	public static Team changeTurn(Team turn) {
		if (WHITE == turn) {
			return BLACK;
		}
		return WHITE;
	}

	public static boolean isWhite(Team team) {
		return WHITE.equals(team);
	}

	public boolean isOurTeam(Team team) {
		return this.equals(team);
	}
}
