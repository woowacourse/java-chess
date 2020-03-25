package chess.piece.type;

import chess.piece.location.strategy.LocationStrategy;
import chess.piece.location.strategy.RookLocationsStrategy;

public class Rook extends Piece {
	private static final char name = 'r';
	private static final LocationStrategy LOCATION_STRATEGY = new RookLocationsStrategy();

	public Rook(boolean isBlack) {
		super(p(isBlack), LOCATION_STRATEGY);
	}

	private static char p(boolean isBlack) {
		if (isBlack) {
			return Character.toUpperCase(name);
		}
		return name;
	}
}
