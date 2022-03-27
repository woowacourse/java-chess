package chess.domain.piece;

public enum Symbol {
	EMPTY(".", ".", 0),
	PAWN("P", "p", 1),
	ROOK("R", "r", 5),
	BISHOP("B", "b", 3),
	KNIGHT("N", "n", 2.5),
	QUEEN("Q", "q", 9),
	KING("K", "k", 0);

	private final String black;
	private final String white;
	private final double score;

	Symbol(String black, String white, double score) {
		this.black = black;
		this.white = white;
		this.score = score;
	}

	public String getBlack() {
		return black;
	}

	public String getWhite() {
		return white;
	}

	public double getScore() {
		return score;
	}
}
