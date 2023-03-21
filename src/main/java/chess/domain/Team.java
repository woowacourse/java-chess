package chess.domain;

public enum Team {

	BLACK, WHITE, EMPTY;

	public static boolean isNotEmptyDifferentTeam(final Team team1, final Team team2) {
		return (team1.isWhite() && team2.isBlack() || team1.isBlack() && team2.isWhite());
	}

	public boolean isBlack(){
		return this == BLACK;
	}

	public boolean isWhite(){
		return this == WHITE;
	}
}
