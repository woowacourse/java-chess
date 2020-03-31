package chess.domain.piece;

public enum Color {
	WHITE("1"),
	BLACK("8");

	private final String initialRow;

	Color(String initialRow) {
		this.initialRow = initialRow;
	}

	public String getInitialRow() {
		return initialRow;
	}

	public Color reverse() {
		if (this == WHITE) {
			return BLACK;
		}
		return WHITE;
	}
}
