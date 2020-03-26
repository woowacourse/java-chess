package chess.piece.type;

import chess.board.Location;
import chess.piece.location.strategy.KingLocationStrategy;
import chess.piece.location.strategy.LocationStrategy;

public class King extends Piece {
	private static final char name = 'k';
	private static final LocationStrategy LOCATION_STRATEGY = new KingLocationStrategy();

	public King(boolean isBlack) {
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
		return now.isKingRange(after);
	}
}
