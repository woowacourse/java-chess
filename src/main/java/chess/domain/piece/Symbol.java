package chess.domain.piece;

public enum Symbol {
	DOT(".", "."),
	PAWN("P", "p"),
	ROOK("R", "r"),
	BISHOP("B", "b"),
	KNIGHT("N", "n"),
	QUEEN("Q", "q"),
	KING("K", "k");

	private final String black;
	private final String white;

	Symbol(String black, String white) {
		this.black = black;
		this.white = white;
	}

	public String getBlack() {
		return black;
	}

	public String getWhite() {
		return white;
	}
}
