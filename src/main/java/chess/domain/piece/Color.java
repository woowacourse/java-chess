package chess.domain.piece;

public enum Color {
	WHITE,
	BLACK;

	public Color getOpponent() {
		if (this == WHITE) {
			return BLACK;
		}
		return WHITE;
	}
}
