package chess.domain.piece;

public enum Type {
	PAWN("P", 1),
	QUEEN("Q", 9),
	KING("K", 0),
	BISHOP("B", 3),
	KNIGHT("N", 2.5),
	ROOK("R", 5),
	BLANK(".", 0);

	private final String name;
	private final double score;

	Type(String name, double score) {
		this.name = name;
		this.score = score;
	}

	public String nameByColor(Color color) {
		if (color == Color.WHITE) {
			return name.toLowerCase();
		}
		return name;
	}

	public double getScore() {
		return score;
	}
}
