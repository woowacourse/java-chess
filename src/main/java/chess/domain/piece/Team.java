package chess.domain.piece;

public enum Team {
	BLACK,
	WHITE,
	NONE;

	Team() {
	}

	public boolean isSame(Team team) {
		return this == team;
	}

	public boolean isNotNone() {
		return this != NONE;
	}
}
