package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.movable.PawnMovable;

public class Pawn extends Piece {
	private static final int PAWN_SCORE = 1;

	public Pawn(Position position, String name, Color color) {
		super(position, name, new PawnMovable(), color, PAWN_SCORE);
	}

	@Override
	public boolean isPawn() {
		return true;
	}
}
