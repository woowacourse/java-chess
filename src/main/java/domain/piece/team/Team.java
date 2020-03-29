package domain.piece.team;

public enum Team {
	BLACK, WHITE;

	public static Team changeTurn(Team turn) {
		if (WHITE == turn) {
			return BLACK;
		}
		return WHITE;
	}
}
