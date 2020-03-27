package chess.domain.piece;

import chess.domain.position.PositionFactory;
import chess.domain.piece.movable.KingMovable;


public class Blank extends Piece {
	public Blank() {
		super(PositionFactory.of("a1"), ".", new KingMovable(), Color.BLANK, 0);
	}
}
