package chess.domain.piece;

public enum Symbol {

	EMPTY(".", 0),
	PAWN("p", 1),
	ROOK("r", 5),
	BISHOP("b", 3),
	KNIGHT("n", 2.5),
	QUEEN("q", 9),
	KING("k", 0);

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
