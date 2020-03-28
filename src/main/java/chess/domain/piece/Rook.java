package chess.domain.piece;

import chess.domain.piece.movable.BlockedMovable;
import chess.domain.piece.movable.Directions;
import chess.domain.position.Position;

public class Rook extends Piece {
	private static final int ROOK_SCORE = 5;

	public Rook(Position position, String name, Color color) {
		super(position, name, new BlockedMovable(Directions.LINEAR), color, ROOK_SCORE);
	}
}
