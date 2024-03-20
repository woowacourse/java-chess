package domain;

public enum Team {
	WHITE, BLACK;

	public Team otherTeam() {
		if (this.equals(WHITE)) {
			return BLACK;
		}
		return WHITE;
	}
}
