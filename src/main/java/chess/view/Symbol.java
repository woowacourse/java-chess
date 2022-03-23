package chess.view;

public enum Symbol {
	BISHOP("b"),
	KING("k"),
	KNIGHT("n"),
	PAWN("p"),
	QUEEN("q"),
	ROOK("r"),
	BLANK(".");

	final String value;

	Symbol(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
