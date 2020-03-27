package chess.domain.piece;

import chess.domain.piece.movable.Directions;
import chess.domain.piece.movable.UnblockedMovable;
import chess.domain.position.PositionFactory;


public class Blank extends Piece {
	public Blank() {
		super(PositionFactory.of("a1"), ".", new UnblockedMovable(Directions.NONE), Color.BLANK, 0);
	}
}
