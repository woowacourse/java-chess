package chess.domain.piece;

public enum Color {
	WHITE,
	BLACK;

	public Color reverse() {
		if (this.equals(WHITE)) {
			return BLACK;
		}
		return WHITE;
	}
}
