package chess.domain.piece;

public enum Symbol {
	DOT("."),
	PAWN("P"),
	ROOK("R"),
	BISHOP("B"),
	KNIGHT("N"),
	QUEEN("Q"),
	KING("K");

	private final String value;

	Symbol(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
