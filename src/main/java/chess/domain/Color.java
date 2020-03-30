package chess.domain;

public enum Color {
	WHITE,
	BLACK;

	public static Color reverse(Color color) {
		if (color == WHITE) {
			return BLACK;
		}
		return WHITE;
	}
}
