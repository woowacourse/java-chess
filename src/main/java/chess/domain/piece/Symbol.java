package chess.domain.piece;

public enum Symbol {

	EMPTY(".", 0),
	PAWN("P", 1),
	ROOK("R", 5),
	BISHOP("B", 3),
	KNIGHT("N", 2.5),
	QUEEN("Q", 9),
	KING("K", 0);

	private final String name;
	private final double score;

	Symbol(String name, double score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public double getScore() {
		return score;
	}
}
