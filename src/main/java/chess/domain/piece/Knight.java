package chess.domain.piece;

import chess.domain.piece.movable.Directions;
import chess.domain.piece.movable.UnblockedMovable;
import chess.domain.position.Position;

public class Knight extends Piece {
	public Knight(Position position, String name, Color color) {
		super(position, name, new UnblockedMovable(Directions.KNIGHT), color, 2.5);
	}
}
