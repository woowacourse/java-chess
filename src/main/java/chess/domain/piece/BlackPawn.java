package chess.domain.piece;

public class BlackPawn extends Piece {
	public static final int PAWN_SCORE = 1;

	public BlackPawn() {
		super(Color.BLACK, "P", new PawnMovingStrategy(Color.BLACK), PAWN_SCORE);
	}
}
