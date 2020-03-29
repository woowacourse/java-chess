package chess.domain.piece;

import chess.domain.piece.movable.Directions;
import chess.domain.piece.movable.UnblockedMovable;
import chess.domain.position.PositionFactory;


public class Blank extends Piece {
	private static final int BLANK_SCORE = 0;
	private static final String BLANK_DEFAULT_POSITION = "a1";
	private static final String BLANK_NAME = ".";

	public Blank() {
		super(PositionFactory.of(BLANK_DEFAULT_POSITION), BLANK_NAME, new UnblockedMovable(Directions.NONE), Color.NONE, BLANK_SCORE);
	}
}
