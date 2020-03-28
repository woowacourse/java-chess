package chess.domain.piece;

import chess.domain.piece.movable.Directions;
import chess.domain.piece.movable.UnblockedMovable;
import chess.domain.position.Position;

public class King extends Piece {
	public King(Position position, String name, Color color) {
		super(position, name, new UnblockedMovable(Directions.EVERY), color, 0);
	}

	@Override
	public boolean isKing() {
		return true;
	}
}
