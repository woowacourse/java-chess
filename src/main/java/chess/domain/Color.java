package chess.domain;

public enum Color {
	WHITE,
	BLACK,
	NONE;

	public Color reverse() {
		if (this == WHITE) {
			return BLACK;
		}
		return WHITE;
	}
}
