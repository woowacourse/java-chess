package chess.domain.piece;

import chess.domain.piece.movable.Directions;
import chess.domain.piece.movable.UnblockedMovable;
import chess.domain.position.Position;

public class Knight extends Piece {
	private static final double KNIGHT_SCORE = 2.5;

	public Knight(Position position, String name, Color color) {
		super(position, name, new UnblockedMovable(Directions.KNIGHT), color, KNIGHT_SCORE);
	}
}
