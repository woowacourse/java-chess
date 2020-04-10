package chess.domain.piece;

public class Pawn extends Piece {
	public static final double PAWN_SCORE = 1;

	public Pawn(Color color) {
		super(color, "p", new PawnMovingStrategy(color), PAWN_SCORE);
	}
}
