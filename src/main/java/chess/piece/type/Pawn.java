package chess.piece.type;

import chess.board.Location;
import chess.piece.location.strategy.LocationStrategy;
import chess.piece.location.strategy.PawnLocationStrategy;

public class Pawn extends Piece {
	private static final char name = 'p';
	private static final LocationStrategy LOCATION_STRATEGY = new PawnLocationStrategy();

	public Pawn(boolean isBlack) {
		super(p(isBlack), LOCATION_STRATEGY);
	}

	private static char p(boolean isBlack) {
		if (isBlack) {
			return Character.toUpperCase(name);
		}
		return name;
	}

	@Override
	public boolean canMove(Location now, Location after) {
		// TODO : 이거 구현해야함
		return false;
	}
}
