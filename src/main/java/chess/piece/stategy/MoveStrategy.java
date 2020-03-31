package chess.piece.stategy;

import chess.board.Location;

public abstract class MoveStrategy {
	public void checkMove(Location now, Location destination, boolean destinationHasEnemy) {
		checkRange(now, destination);
		checkStrategy(now, destination, destinationHasEnemy);
	}

	abstract void checkRange(Location now, Location destination);

	abstract void checkStrategy(Location now, Location destination,
		boolean destinationHasEnemy); // 목적지가 적인가 만 물어보면되나? 근데 목적지가 동료라면..?
}
