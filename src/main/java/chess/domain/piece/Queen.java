package chess.domain.piece;

import chess.domain.piece.movable.BlockedMovable;
import chess.domain.piece.movable.Directions;
import chess.domain.position.Position;

public class Queen extends Piece {
	public Queen(Position position, String name, Color color) {
		super(position, name, new BlockedMovable(Directions.EVERY), color, 9);
	}
}
