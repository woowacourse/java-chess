package chess.domain.color;

public enum Color {

	BLACK,
	WHITE,
	NONE;

	public Color switchTurn() {
		if (this == BLACK) {
			return WHITE;
		}
		return BLACK;
	}
}
