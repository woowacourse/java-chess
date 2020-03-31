package chess.domain.piece;

import static chess.domain.piece.BlackPawn.*;

public class WhitePawn extends Piece {
	public WhitePawn() {
		super(Color.WHITE, "p", new PawnMovingStrategy(Color.WHITE), PAWN_SCORE);
	}
}
