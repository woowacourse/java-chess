package chess.piece.stategy;

import chess.board.Location;
import chess.team.Team;

public abstract class MoveStrategy {
	protected final Team team;

	protected MoveStrategy(Team team) {
		this.team = team;
	}

	public void checkMove(Location now, Location destination, boolean destinationHasEnemy) {
		checkRange(now, destination);
		checkStrategy(now, destination, destinationHasEnemy);
	}

	abstract void checkRange(Location now, Location destination);

	abstract void checkStrategy(Location now, Location destination,
		boolean destinationHasEnemy);
}
