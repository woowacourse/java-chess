package chess.domain.piece;

public enum Color {
	WHITE("white"),
	BLACK("black"),
	BLANK("blank");

	private final String name;

	Color(String name) {
		this.name = name;
	}

	public Color changeColor() {
		if (isWhite()) {
			return BLACK;
		}
		if (isBlack()) {
			return WHITE;
		}
		return BLANK;
	}

	public boolean isWhite() {
		return WHITE.equals(this);
	}

	public boolean isBlack() {
		return BLACK.equals(this);
	}

	public boolean isBlank() {
		return BLANK.equals(this);
	}

	public boolean isSame(Color color) {
		return this.equals(color);
	}

	public String getName() {
		return name;
	}
}
