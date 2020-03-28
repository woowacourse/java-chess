package chess.domain.piece;

import chess.domain.piece.movable.BlockedMovable;
import chess.domain.piece.movable.Directions;
import chess.domain.position.Position;

public class Queen extends Piece {
	private static final int QUEEN_SCORE = 9;

	public Queen(Position position, String name, Color color) {
		super(position, name, new BlockedMovable(Directions.EVERY), color, QUEEN_SCORE);
	}
}
