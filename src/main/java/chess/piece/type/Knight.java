package chess.piece.type;

import chess.piece.location.strategy.KnightLocationStrategy;
import chess.piece.location.strategy.LocationStrategy;

public class Knight extends Piece {
	private static final char name = 'n';
	private static final LocationStrategy LOCATION_STRATEGY = new KnightLocationStrategy();

	public Knight(boolean isBlack) {
		super(p(isBlack), LOCATION_STRATEGY);
	}

	private static char p(boolean isBlack) {
		if (isBlack) {
			return Character.toUpperCase(name);
		}
		return name;
	}
}
