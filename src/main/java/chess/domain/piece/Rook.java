package chess.domain.piece;

import chess.domain.piece.movable.BlockedMovable;
import chess.domain.piece.movable.Directions;
import chess.domain.position.Position;

public class Rook extends Piece {
	public Rook(Position position, String name, Color color) {
		super(position, name, new BlockedMovable(Directions.LINEAR), color, 5);
	}
}
