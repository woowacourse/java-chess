package chess.domain;

public enum Team {

	BLACK, WHITE, EMPTY;

	public Team alternate() {
		if (this == WHITE) {
			return BLACK;
		}
		if (this == BLACK) {
			return WHITE;
		}
		return EMPTY;
	}
}
