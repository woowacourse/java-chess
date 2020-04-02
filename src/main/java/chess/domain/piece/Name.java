package chess.domain.piece;

public enum Name {
	KING("K", "♚", "♔"),
	QUEEN("Q", "♛", "♕"),
	ROOK("R", "♜", "♖"),
	BISHOP("B", "♝", "♗"),
	KNIGHT("N", "♞", "♘"),
	PAWN("P", "♟", "♙"),
	EMPTY(".", "", "");

	private final String name;
	private final String blackSymbol;
	private final String whiteSymbol;

	Name(String name, String blackSymbol, String whiteSymbol) {
		this.name = name;
		this.blackSymbol = blackSymbol;
		this.whiteSymbol = whiteSymbol;
	}

	public String getName() {
		return name;
	}

	public String getBlackSymbol() {
		return blackSymbol;
	}

	public String getWhiteSymbol() {
		return whiteSymbol;
	}
}