package domain;

public enum Team {
	WHITE, BLACK;

	Team otherTeam() {
		if (this.equals(WHITE)) {
			return BLACK;
		}
		return WHITE;
	}
}
