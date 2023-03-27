package chess.domain.piece;

public enum PieceType {
	ROOK(5.0),
	KNIGHT(2.5),
	BISHOP(3.0),
	QUEEN(9.0),
	PAWN(1.0),
	PAWNS(0.5),
	EMPTY(0.0),
	KING(0.0);

	private final double score;

	PieceType(double score) {
		this.score = score;
	}

	public double score() {
		return score;
	}
}
