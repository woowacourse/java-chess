package chess.domain.piece;

public enum PieceScore {
	KING_SCORE(0),
	QUEEN_SCORE(9),
	ROOK_SCORE(5),
	BISHOP_SCORE(3),
	KNIGHT_SCORE(2.5),
	PAWN_SCORE(1);

	private final double score;

	PieceScore(double score) {
		this.score = score;
	}

	public double getScore() {
		return score;
	}

	public boolean isSameScore(double score) {
		return this.score == score;
	}
}
