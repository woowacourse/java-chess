package chess.domain.feature;

public enum Type {
	PAWN("P", 1),
	QUEEN("Q", 9),
	KING("K", 0),
	BISHOP("B", 3),
	KNIGHT("N", 2.5),
	ROOK("R", 5),
	BLANK(".", 0);

	private final String initial;
	private final double score;

	Type(String initial, double score) {
		this.initial = initial;
		this.score = score;
	}

	public String nameByColor(Color color) {
		if (color == Color.WHITE) {
			return initial.toLowerCase();
		}
		return initial;
	}

	public double getScore() {
		return score;
	}

	public String getName() {
		return this.name();
	}
}
