package chess.piece;

public enum PieceNameType {
	PAWN("p"),
	KING("k"),
	QUEEN("q"),
	ROOK("r"),
	BISHOP("b"),
	KNIGHT("k");

	private final String value;

	PieceNameType(String value) {
		this.value = value;
	}
}
