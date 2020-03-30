package domain.pieces;

public enum PieceType {
	BISHOP(3, "B"),
	KING(0, "K"),
	KNIGHT(2.5, "N"),
	PAWN(2, "P"),
	QUEEN(9, "Q"),
	ROOK(5, "R");

	public static final int HALF_DIVIDER = 2;

	private final double score;
	private final String initial;

	PieceType(double score, String initial) {
		this.score = score;
		this.initial = initial;
	}

	public static double getPawnHalfScore() {
		return PAWN.score / HALF_DIVIDER;
	}

	public double getScore() {
		return score;
	}

	public String getInitial() {
		return initial;
	}
}
