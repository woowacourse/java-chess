package chess.domain.piece;

import chess.domain.piece.movable.BlockedMovable;
import chess.domain.piece.movable.Directions;
import chess.domain.position.Position;


public class Bishop extends Piece {
	private static final int BISHOP_SCORE = 3;

	public Bishop(Position position, String name, Color color) {
		super(position, name, new BlockedMovable(Directions.DIAGONAL), color, BISHOP_SCORE);
	}
}
