package chess.domain.feature;

public enum Color {
	BLACK("black"),
	WHITE("white"),
	NO_COLOR("blank");

	private final String color;

	Color(String color) {
		this.color = color;
	}

	public boolean isWhite() {
		return this.equals(WHITE);
	}

	public boolean isBlack() {
		return this.equals(BLACK);
	}

	public Color getOppositeColor() {
		if (this.isBlack()) {
			return Color.WHITE;
		}
		if (this.isWhite()) {
			return Color.BLACK;
		}
		throw new IllegalArgumentException();
	}

	public String getColor() {
		return color;
	}
}
