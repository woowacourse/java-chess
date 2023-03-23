package chess.domain.piece;

public enum PieceType {

	EMPTY(0),
	KING(0),
	QUEEN(90),
	ROOK(50),
	KNIGHT(25),
	BISHOP(30),
	PAWN(10),
	INITIAL_PAWN(10);

	private final int scoreMultipliedBy10;

	PieceType(final int scoreMultipliedBy10) {
		this.scoreMultipliedBy10 = scoreMultipliedBy10;
	}

	public static int getScoreMultipliedBy10(PieceType pieceType) {
		return pieceType.scoreMultipliedBy10;
	}
}
